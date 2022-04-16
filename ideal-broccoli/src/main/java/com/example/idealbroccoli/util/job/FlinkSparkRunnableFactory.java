package com.example.idealbroccoli.util.job;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlinkSparkRunnableFactory {

    @Autowired
    RecordService recordService;

    public Runnable newJob(Job jobDesc) {
        if (jobDesc.getJobType().equals("flink")) {
            return new FlinkRunnable(jobDesc, recordService);
        } else if (jobDesc.getJobType().equals("spark")) {
            return new SparkRunnable(jobDesc, recordService);
        } else {
            throw new RuntimeException();
        }
    }
}
