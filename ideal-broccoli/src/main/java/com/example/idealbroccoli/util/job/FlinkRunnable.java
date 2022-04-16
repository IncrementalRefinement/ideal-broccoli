package com.example.idealbroccoli.util.job;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.entity.Record;
import com.example.idealbroccoli.service.RecordService;
import org.apache.flink.client.deployment.StandaloneClusterId;
import org.apache.flink.client.program.PackagedProgram;
import org.apache.flink.client.program.PackagedProgramUtils;
import org.apache.flink.client.program.ProgramInvocationException;
import org.apache.flink.client.program.rest.RestClusterClient;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.JobManagerOptions;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.runtime.jobgraph.JobGraph;

import java.io.File;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;


public class FlinkRunnable implements Runnable {


    private static final String CLUSTER_HOST = "localhost";

    private static final int CLUSTER_PORT = 8081;
    private final Job jobDesc;
    private final RecordService recordService;

    protected FlinkRunnable(Job jobDesc, RecordService recordService) {
        this.jobDesc = jobDesc;
        this.recordService = recordService;
    }

    @Override
    public void run() {
        // execute the job
        LocalDateTime beginTime = LocalDateTime.now();

        Configuration conf = new Configuration();
        conf.setString(JobManagerOptions.ADDRESS, CLUSTER_HOST);
        conf.setInteger(RestOptions.PORT, CLUSTER_PORT);

        PackagedProgram packagedProgram = null;
        try {
            packagedProgram = PackagedProgram.newBuilder()
                    .setJarFile(new File(jobDesc.getJarFilePath()))
                    .build();
        } catch (ProgramInvocationException e) {
            throw new RuntimeException(e);
        }

        JobGraph jobGraph = null;
        try {
            jobGraph = PackagedProgramUtils.createJobGraph(packagedProgram, conf, 1, false);
        } catch (ProgramInvocationException e) {
            throw new RuntimeException(e);
        }

        // TODO
        RestClusterClient client = null;
        try {
            client = new RestClusterClient(conf, StandaloneClusterId.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            client.submitJob(jobGraph).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // create the record
        Record record = new Record();
        record.setJobId(jobDesc.getId());
        record.setBeginTime(beginTime);
        // TODO
        record.setEndTime(null);
        record.setSuccess(true);
        // TODO
        recordService.createNewRecord(record);
    }
}
