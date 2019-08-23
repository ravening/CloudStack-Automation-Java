package com.rakeshv.cloudstackautomation.controllers;

import com.rakeshv.cloudstackautomation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/username/{name}")
    public ResponseEntity<?> getUserByUserName(@PathVariable("name") String name) {
        log.info("Searching for user with username {}", name);
        String response = userService.searchUser(name);

        log.info("Response is {}", response);
        return ResponseEntity.ok(response);
    }
}
