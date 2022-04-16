package com.example.idealbroccoli.util.job;

import com.example.idealbroccoli.entity.Job;
import com.example.idealbroccoli.service.KafkaService;
import com.example.idealbroccoli.util.serilization.JobJsonSerialization;

public class EventProducerRunnable extends RunnableWithId {

    private final Job jobDesc;
    private final KafkaService kafkaService;

    protected EventProducerRunnable(Job jobDesc, KafkaService kafkaService) {
        super(jobDesc.getId());
        this.jobDesc = jobDesc;
        this.kafkaService = kafkaService;
    }

    @Override
    public void run() {
        kafkaService.sendMessage(JobJsonSerialization.serialize(this.jobDesc));
    }
}
