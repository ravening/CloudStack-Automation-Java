package com.rakeshv.cloudstackautomation.controllers;

import com.rakeshv.cloudstackautomation.service.DomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/domain")
public class DomainController {
    @Autowired
    DomainService domainService;

    @GetMapping("/{name}")
    public ResponseEntity<?> searchForDomain(@PathVariable("name") String name) {
        log.info("Searching for domain name {}", name);
        String response = domainService.searchDomain(name);

        log.info("Response is {}", response);
        return ResponseEntity.ok(response);
    }
}
