package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.service.SchedulerService;
import com.example.idealbroccoli.util.job.RunnableWithId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final Map<Long, ScheduledFuture<?>> registeredSchedulers = new HashMap<>();

    @Autowired
    ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public void registerJob(RunnableWithId job, long rate) {
        ScheduledFuture<?> future = threadPoolTaskScheduler.scheduleAtFixedRate(job, rate);
        registeredSchedulers.put(job.getId(), future);
    }


    @Override
    public void executeJobOnce(Runnable job) {
        threadPoolTaskScheduler.schedule(job, new Date());
    }

    public void cancelJob(long jobId) {
        ScheduledFuture<?> future = registeredSchedulers.get(jobId);
        future.cancel(false);
        registeredSchedulers.remove(jobId);
    }
}
