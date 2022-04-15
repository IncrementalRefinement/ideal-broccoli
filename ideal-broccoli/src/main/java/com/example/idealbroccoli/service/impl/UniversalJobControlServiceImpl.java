package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.FlinkService;
import com.example.idealbroccoli.service.SparkService;
import com.example.idealbroccoli.service.UniversalJobControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversalJobControlServiceImpl implements UniversalJobControlService {

    @Autowired
    FlinkService flinkService;

    @Autowired
    SparkService sparkService;

    @Override
    public void addJob(Job theJob) {
        String jobType = theJob.getJobType();
        if (jobType.equals("flink")) {
            flinkService.addJob(theJob);
        } else if (jobType.equals("spark")) {
            sparkService.addJob(theJob);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteJob(Job theJob) {
        String jobType = theJob.getJobType();
        if (jobType.equals("flink")) {
            flinkService.deleteJob(theJob);
        } else if (jobType.equals("spark")) {
            sparkService.deleteJob(theJob);
        } else {
            throw new RuntimeException();
        }
    }
}
