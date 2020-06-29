package com.rakeshv.cloudstackautomation.service;

import com.rakeshv.cloudstackautomation.models.Command;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    CommandBuilderService commandBuilderService;

    public String searchUser(String userName) {
        return searchUserOnAllPlatforms(userName);
    }

    private String searchUserOnAllPlatforms(String userName) {
        HashMap<String,String> parameters = new HashMap<>();
        parameters.putIfAbsent("username", userName);
        parameters.putIfAbsent("listall", "true");
        try {
            return commandBuilderService.executeonAllPlatforms("listUsers", parameters);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }
}
