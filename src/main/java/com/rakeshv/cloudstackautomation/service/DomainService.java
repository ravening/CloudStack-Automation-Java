package com.rakeshv.cloudstackautomation.service;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomainService {
    @Autowired
    CommandBuilderService commandBuilderService;

    public String searchDomain(String domainName) {
        return searchDomainOnAllPlatforms(domainName);
    }

    private String searchDomainOnAllPlatforms(String domainName) {
        HashMap<String,String> parameters = new HashMap<>();
        parameters.putIfAbsent("keyword", domainName);
        parameters.putIfAbsent("listall", "true");

        try {
            return commandBuilderService.executeonAllPlatforms("listDomains", parameters);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
