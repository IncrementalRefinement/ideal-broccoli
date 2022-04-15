package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.RecordService;
import com.example.idealbroccoli.service.SchedulerService;
import com.example.idealbroccoli.service.SparkService;
import com.example.idealbroccoli.util.job.RunnableWithId;
import com.example.idealbroccoli.util.job.SparkRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparkServiceImpl implements SparkService {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    RecordService recordService;

    @Override
    public void addJob(Job theJob) {
        RunnableWithId runnableWithId = constructSparkRunnable(theJob);
        schedulerService.registerTask(runnableWithId, theJob.getExecuteRate());
    }

    @Override
    public void deleteJob(Job theJob) {
        schedulerService.cancelTask(theJob.getId());
    }

    private RunnableWithId constructSparkRunnable(Job theJob) {
        return new SparkRunnable(theJob, recordService);
    }
}
