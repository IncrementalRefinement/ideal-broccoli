package com.example.idealbroccoli.util.job;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventProducerRunnableFactory {

    @Autowired
    KafkaService kafkaService;

    public EventProducerRunnable newJob (Job jobDesc) {
        return new EventProducerRunnable(jobDesc, kafkaService);
    }
}
