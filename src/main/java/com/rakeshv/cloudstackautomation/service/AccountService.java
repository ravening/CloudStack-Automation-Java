package com.rakeshv.cloudstackautomation.service;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    CommandBuilderService commandBuilderService;

    public String searchAccount(String accountName) {
        return searchAccountOnAllPlatforms(accountName);
    }

    private String searchAccountOnAllPlatforms(String accountName) {
        HashMap<String,String> parameters = new HashMap<>();
        parameters.putIfAbsent("keyword", accountName);
        parameters.putIfAbsent("listall", "true");
        try {
            return commandBuilderService.executeonAllPlatforms("listAccounts", parameters);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
