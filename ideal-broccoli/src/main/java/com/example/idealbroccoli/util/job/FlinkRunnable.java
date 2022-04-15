package com.example.idealbroccoli.util.job;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.RecordService;


public class FlinkRunnable extends RunnableWithId {

    private Job jobDesc;
    private RecordService recordService;

    public FlinkRunnable(Job jobDesc, RecordService recordService) {
        super(jobDesc.getId());
        this.jobDesc = jobDesc;
        this.recordService = recordService;
    }

    @Override
    public void run() {

    }
}
