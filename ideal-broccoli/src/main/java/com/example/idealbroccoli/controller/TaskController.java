package com.example.idealbroccoli.controller;

import com.example.idealbroccoli.service.KafkaService;
import com.example.idealbroccoli.service.SchedulerService;
import com.example.idealbroccoli.util.job.TestRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    KafkaService kafkaService;

    @Autowired
    SchedulerService schedulerService;

    @GetMapping(value = "/test/generate")
    public String generateEvent() {
        kafkaService.sendMessage("test");
        return null;
    }

    @GetMapping(value = "/test/register")
    public String registerTask() {
        TestRunnable newRunnable = new TestRunnable(1, "test runnable!!!");
        schedulerService.registerJob(newRunnable, 1000);
        return null;
    }

    @GetMapping(value = "/test/cancel")
    public String cancelTask() {
        schedulerService.cancelJob(1);
        return null;
    }
}
