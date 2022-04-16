package com.example.idealbroccoli.config;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.JobService;
import com.example.idealbroccoli.service.impl.UniversalJobControlServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class InitializationConfig {

    @Autowired
    JobService jobService;

    @Autowired
    UniversalJobControlServiceImpl jobController;

    @PostConstruct
    public void initializeJobs() {
        List<Job> jobs = jobService.queryAllJob();
        for (Job job : jobs) {
            if (job.getScheduledJob()) {
                jobController.addJob(job);
            }
        }
    }
}
