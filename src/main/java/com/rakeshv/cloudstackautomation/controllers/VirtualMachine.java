package com.rakeshv.cloudstackautomation.controllers;

import com.rakeshv.cloudstackautomation.service.CommandBuilderService;
import com.rakeshv.cloudstackautomation.service.VirtualMachineService;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/virtualmachines")
@Slf4j
public class VirtualMachine {
    @Autowired
    private VirtualMachineService virtualMachineService;

    @Autowired
    CommandBuilderService commandBuilderService;

    @GetMapping("/listall")
    public ResponseEntity<?> getAllMachines() {
        String response = commandBuilderService.executeCommand("listVirtualMachines", "csrp01");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findVirtualMachineByName(@PathVariable("name") String name) {
        HashMap<String, String> optionalParameters = new HashMap<>();
        optionalParameters.putIfAbsent("keyword", name);
        optionalParameters.putIfAbsent("listall", "true");

        log.info("Searching for virtual machine with name {}", name);
        String response = virtualMachineService.findVirtualmachine(optionalParameters);

        if (!response.contains("count")) {
            log.info("unable to find the virtual machine");
            response = null;
        }
        return ResponseEntity.ok(response);
    }
}
