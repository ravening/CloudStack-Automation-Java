package com.rakeshv.cloudstackautomation.service;

import com.rakeshv.cloudstackautomation.models.CloudstackHandle;
import com.rakeshv.cloudstackautomation.models.Command;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommandBuilderService {
    @Autowired
    private CloudstackApi cloudstackApi;
    @Autowired
    private Environment environment;

    private static final String USA = "usa";
    private static final String EUROPE = "europe";
    private static final String ASIA = "asia";

    public HashMap<String, CloudstackHandle> platformMap;
    List<Callable<String>> callableList = new ArrayList<>();
    List<String> resultList = new ArrayList<>();
    static String[] platforms = new String[]{USA, EUROPE, ASIA};
    static final int NUMBER_OF_THREADS = platforms.length;

    @PostConstruct
    private void constructHandlers() {
        platformMap = new HashMap<>();

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

    public String executeonAllPlatforms(String command, HashMap<String, String> parameters) {
        ArrayList<String> arrayList = new ArrayList<>();
        callableList = Arrays.asList(platforms)
                .stream()
                .parallel()
                .map(name -> {
                    Callable<String> callable = () -> {
                        String response = executeCommand(command, parameters, name);
                        if (response.contains("count")) {
                            log.info("Response from {} is {}", name, response);
                            response = "{\"" + name + "\":" + response + "}";
                            arrayList.add(response);
                            return response;
                        }
                        return null;
                    };
                    return callable;
                })
                .collect(Collectors.toList());

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        try {
            List<Future<String>> futures = executorService.invokeAll(callableList);
//            for (Future<String> future : futures) {
//                try {
//                    arrayList.add(future.get());
//                } catch (ExecutionException e) {
//                    log.error("Exception happneed {}", e.getLocalizedMessage());
//                }
//            }
        } catch (InterruptedException e) {
            log.error("Exception happened {}", e.getMessage());
        }

        String finalResponse = String.join(",", arrayList);
        return "[" + finalResponse + "]";
    }
}
