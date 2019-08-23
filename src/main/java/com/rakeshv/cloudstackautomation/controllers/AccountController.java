package com.rakeshv.cloudstackautomation.controllers;

import com.rakeshv.cloudstackautomation.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/{name}")
    public ResponseEntity<?> searchForAccount(@PathVariable("name") String name) {
        log.info("Searching for the account with name {}", name);
        String response = accountService.searchAccount(name);

        log.info("Response is {}", response);
        return ResponseEntity.ok(response);
    }
}
