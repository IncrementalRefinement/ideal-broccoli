package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.FlinkService;
import com.example.idealbroccoli.service.RecordService;
import com.example.idealbroccoli.service.SchedulerService;
import com.example.idealbroccoli.util.job.FlinkRunnable;
import com.example.idealbroccoli.util.job.RunnableWithId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlinkServiceImpl implements FlinkService {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    RecordService recordService;

    @Override
    public void addJob(Job theJob) {
        RunnableWithId runnableWithId = constructFlinkRunnable(theJob);
        if (theJob.getScheduledJob()) {
            schedulerService.registerJob(runnableWithId, theJob.getExecuteRate());
        } else {
            schedulerService.executeJobOnce(runnableWithId);
        }
    }

    @Override
    public void deleteJob(Job theJob) {
        schedulerService.cancelJob(theJob.getId());
    }

    private RunnableWithId constructFlinkRunnable(Job theJob) {
        return new FlinkRunnable(theJob, recordService);
    }
}
