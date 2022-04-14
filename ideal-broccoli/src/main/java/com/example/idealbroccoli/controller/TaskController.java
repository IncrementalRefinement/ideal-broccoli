package com.example.idealbroccoli.controller;

import com.example.idealbroccoli.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    KafkaService kafkaService;

    @GetMapping(value = "/test/generate")
    public String generateEvent() {
        kafkaService.sendMessage("test");
        return null;
    }
}
