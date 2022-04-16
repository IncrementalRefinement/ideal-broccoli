package com.example.idealbroccoli.util.job;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.RecordService;
import com.example.idealbroccoli.util.io.InputStreamReaderRunnable;
import org.apache.spark.launcher.SparkLauncher;

import java.io.IOException;
import java.time.LocalDateTime;

import com.example.idealbroccoli.entity.Record;

public class SparkRunnable implements Runnable {

    final static String JAVA_HOME = "/usr/lib/jvm/java-11-openjdk";
    final static String SPARK_HOME = "/opt/apache-spark";
    final static String DEFAULT_DRIVER_MEMORY = "1g";


    private final Job jobDesc;
    private final RecordService recordService;

    protected SparkRunnable(Job jobDesc, RecordService recordService) {
        this.jobDesc = jobDesc;
        this.recordService = recordService;
    }

    @Override
    public void run() {
        // execute the job
        LocalDateTime beginTime = LocalDateTime.now();

        SparkLauncher sparkLauncher = new SparkLauncher()
                .setVerbose(true)
                .setJavaHome(JAVA_HOME)
                .setSparkHome(SPARK_HOME)
                .setAppResource(jobDesc.getJarFilePath())
                .setMainClass(jobDesc.getMainClassPath())
                .setMaster("local")
                .setConf(SparkLauncher.DRIVER_MEMORY, DEFAULT_DRIVER_MEMORY);

        Process process = null;
        int exitCode = 0;

        try {
            process = sparkLauncher.launch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        InputStreamReaderRunnable inputStreamReaderRunnable = new InputStreamReaderRunnable(process.getInputStream(), "input");
//        Thread inputThread = new Thread(inputStreamReaderRunnable, "LogStreamReader input");
//        inputThread.start();
//
//        InputStreamReaderRunnable errorStreamReaderRunnable = new InputStreamReaderRunnable(process.getErrorStream(), "error");
//        Thread errorThread = new Thread(errorStreamReaderRunnable, "LogStreamReader error");
//        errorThread.start();

        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LocalDateTime endTime = LocalDateTime.now();

        // 2. insert the record
        Record record = new Record();
        record.setJobId(jobDesc.getId());
        record.setBeginTime(beginTime);
        record.setEndTime(endTime);
        record.setSuccess(true);
        recordService.createNewRecord(record);
    }
}
