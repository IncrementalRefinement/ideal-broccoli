package com.example.flinksparkcompdemo.producer;
import com.example.flinksparkcompdemo.utils.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class MyProducer {

    private static final Logger log = LoggerFactory.getLogger(MyProducer.class);

    private final static long DEFAULT_RATE = 1000;

    private static final int DEFAULT_RANGE_MIN = 1000;

    private static final int DEFAULT_RANGE_MAX = 10000;

    private static final SimpleDateFormat DEFAULT_DATA_FORM = new SimpleDateFormat("HH:mm:ss");

    @Value("${spring.kafka.producer.topic_name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    void sendMessage(String topicName, String event) {
        kafkaTemplate.send(topicName, event);
    }

    @Scheduled(fixedRate = DEFAULT_RATE)
    public void reportCurrentTime() {
        String timestamp = DEFAULT_DATA_FORM.format(new Date());
        int randomInt = ThreadLocalRandom.current().nextInt(DEFAULT_RANGE_MIN, DEFAULT_RANGE_MAX);
        MyEvent myEvent = new MyEvent(timestamp, String.valueOf(randomInt));
        sendMessage(topicName, timestamp + " " + myEvent.getMsg());
        log.info("Sent: {}", myEvent);
    }
}
