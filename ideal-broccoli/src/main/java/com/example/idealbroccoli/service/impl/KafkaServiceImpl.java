package com.example.idealbroccoli.service.impl;

import com.example.idealbroccoli.service.KafkaService;
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

    @Override
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
    }

    @Override
    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID)
    public void consumeMessage(String message) {
        // TODO: execute the job and generate the according record
        System.out.println(message);
    }
}
