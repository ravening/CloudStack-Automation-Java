package com.rakeshv.cloudstackautomation.service;

import com.rakeshv.cloudstackautomation.models.CloudstackHandle;
import com.rakeshv.cloudstackautomation.models.Command;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommandBuilderService {
    @Autowired
    private CloudstackApi cloudstackApi;
    @Autowired
    private Environment environment;
    @Value("${cloudstack.platforms}")
    private String platformsList;

    public HashMap<String, CloudstackHandle> platformMap;
    static String[] platforms;
    int NUMBER_OF_THREADS;
    ExecutorService executorService;

    @PostConstruct
    private void constructHandlers() {
        platformMap = new HashMap<>();
        platforms = platformsList.split(",");
        NUMBER_OF_THREADS = platforms.length;
        executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        for (String platform : platforms) {
            String url = environment.getProperty(platform + ".url");
            String apiKey = environment.getProperty(platform + ".apiKey");
            String secretKey = environment.getProperty(platform + ".secretKey");

            CloudstackHandle cloudstackHandle = CloudstackHandle.builder()
                    .url(url)
                    .apiKey(apiKey)
                    .secretKey(secretKey).build();

            platformMap.putIfAbsent(platform, cloudstackHandle);
        }
    }

    public String executeCommand(String command, String platform) {
        Command cloudstackCommand = Command.builder()
                .command(command).build();

        return execute(cloudstackCommand, platform);
    }

    private String executeCommand(String command, HashMap<String, String> parameters, String platform) {
        Command cloustackCommand = Command.builder()
                .command(command)
                .commandParameters(parameters).build();

        return execute(cloustackCommand, platform);
    }

    private String execute(Command command, String platform) {
        CloudstackHandle handle = platformMap.get(platform);
        return cloudstackApi.execute(handle, command);
    }

    private CompletableFuture<String> getResponse(String command, HashMap<String, String> parameters, String platform) {
        return CompletableFuture.supplyAsync( () -> {
            String response = executeCommand(command, parameters, platform);
            if (response.contains("count")) {
                log.info("Response from {} is {}", platform, response);
                response = "{\"" + platform + "\":" + response + "}";
                return response;
            }
            return null;
        }, executorService).exceptionally(ex -> {
            log.error("Something worng {}", ex.getMessage());
            return null;
        });
    }
    public String executeonAllPlatforms(String command, HashMap<String, String> parameters) throws ExecutionException, InterruptedException {
        List<CompletableFuture<String>> completableFutures = Arrays.stream(platforms)
                .map(platform -> getResponse(command, parameters, platform))
                .collect(Collectors.toList());

        CompletableFuture<List<String>> allCompletables =
                CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()]))
                .thenApplyAsync(future -> {
                    return completableFutures.stream().parallel()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList());
                });

        CompletableFuture completableFuture =
                allCompletables.thenApplyAsync(result -> {
                    return result.stream()
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList());
                });

        log.info("final response {}", completableFuture.get());
        return completableFuture.get().toString();
    }
}
