package com.rakeshv.cloudstackautomation.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalSettingService {
    @Autowired
    CommandBuilderService commandBuilderService;

    public String searchGlobalSetting(String name) {
        return searchGlobalSettingNameInternal(name);
    }

    private String searchGlobalSettingNameInternal(String name) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.putIfAbsent("keyword", name);
        return commandBuilderService.executeonAllPlatforms("listConfigurations", parameters);
    }
}
