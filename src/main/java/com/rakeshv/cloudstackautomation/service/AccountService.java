package com.rakeshv.cloudstackautomation.service;

import java.util.HashMap;
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
        return commandBuilderService.executeonAllPlatforms("listAccounts", parameters);
    }
}
