package com.example.idealbroccoli.util.job;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.entity.Record;
import com.example.idealbroccoli.service.RecordService;

public class SparkRunnable extends RunnableWithId {

    private Job jobDesc;
    private RecordService recordService;

    public SparkRunnable(Job jobDesc, RecordService recordService) {
        super(jobDesc.getId());
        this.jobDesc = jobDesc;
        this.recordService = recordService;
    }

    @Override
    public void run() {
        // TODO: 1. execute the job 2. generate the log file 3. generate job execution record
    }
}
