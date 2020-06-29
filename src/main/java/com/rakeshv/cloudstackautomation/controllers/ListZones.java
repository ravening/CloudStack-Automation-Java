package com.rakeshv.cloudstackautomation.controllers;

import com.rakeshv.cloudstackautomation.service.CommandBuilderService;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zones")
public class ListZones {
    @Autowired
    CommandBuilderService commandBuilderService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<String> listZones() throws ExecutionException, InterruptedException {
        HashMap<String, String> parameters = new HashMap<>();
        String response = commandBuilderService.executeonAllPlatforms("listZones", parameters);
        return ResponseEntity.ok(response);
    }
}
