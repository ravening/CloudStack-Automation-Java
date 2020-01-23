package com.rakeshv.cloudstackautomation.controllers;

import com.rakeshv.cloudstackautomation.service.GlobalSettingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/setting")
public class GlobalSettingsController {
    @Autowired
    private GlobalSettingService globalSettingService;

    @GetMapping("/{name}")
    public ResponseEntity<?> searchForSetting(@PathVariable("name") String name) {
        log.info("Searching for the global setting {}", name);
        String response = globalSettingService.searchGlobalSetting(name);

        log.info("Response is {}", response);
        return ResponseEntity.ok(response);
    }
}
