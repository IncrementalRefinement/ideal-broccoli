package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.KafkaService;
import com.example.idealbroccoli.service.SchedulerService;
import com.example.idealbroccoli.util.job.FlinkSparkRunnableFactory;
import com.example.idealbroccoli.util.serilization.JobJsonSerialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {

    // TODO: read this from application.properties
    private static final String GROUP_ID = "spark_event_group";
    private static final String TOPIC_NAME = "spark_event_topic";

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    FlinkSparkRunnableFactory flinkSparkRunnableFactory;


    @Override
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    @Override
    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID)
    public void consumeMessage(String message) {
//        System.out.println(message + "begin");
        Job theJob = JobJsonSerialization.deserialize(message);
        Runnable theRunnableJob = flinkSparkRunnableFactory.newJob(theJob);
        schedulerService.executeJobOnce(theRunnableJob);
//        System.out.println(message + " finished");
    }
}
