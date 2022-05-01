package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.SchedulerService;
import com.example.idealbroccoli.service.SparkService;
import com.example.idealbroccoli.util.job.EventProducerRunnableFactory;
import com.example.idealbroccoli.util.job.RunnableWithId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SparkServiceImpl implements SparkService {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    EventProducerRunnableFactory eventProducerRunnableFactory;

    @Override
    public void addJob(Job theJob) {
        // 生成 RunnableWithId 任务并通过 SchedulerService 加入定时任务
        RunnableWithId runnableWithId = eventProducerRunnableFactory.newJob(theJob);
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
}
