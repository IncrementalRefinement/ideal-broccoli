package com.example.flinksparkcompdemo.producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyProducer {

    private static final Logger log = LoggerFactory.getLogger(MyProducer.class);

    private final static long DEFAULT_RATE = 1000;

    private static final SimpleDateFormat DEFAULT_DATA_FORM = new SimpleDateFormat("HH:mm:ss");

    @Value("${spring.kafka.producer.topic_name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    void sendMessage(String message, String topicName) {
        kafkaTemplate.send(topicName, message);
    }

    @Scheduled(fixedRate = DEFAULT_RATE)
    public void reportCurrentTime() {
        String timestamp = DEFAULT_DATA_FORM.format(new Date());
        sendMessage(timestamp, topicName);
        log.info("Sent: {}", timestamp);
    }
}
