package com.rakeshv.cloudstackautomation.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VirtualMachineService {
    @Autowired
    CommandBuilderService commandBuilderService;
    @Autowired
    private CloudstackApi cloudstackApi;

    public String findVirtualmachine(HashMap<String, String> parameters) {
        return findVirtualMachineOnAllPlatforms(parameters);
    }

    private String findVirtualMachineOnAllPlatforms(HashMap<String, String> parameters) {
        return commandBuilderService.executeonAllPlatforms("listVirtualMachines", parameters);
    }
}
