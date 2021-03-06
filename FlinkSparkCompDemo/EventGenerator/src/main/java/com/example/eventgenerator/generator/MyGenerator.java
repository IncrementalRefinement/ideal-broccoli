package com.example.eventgenerator.generator;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class MyGenerator {
    // Kafka 配置项
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private final static String TOPIC_NAME = "spark_flink_comp_topic_test";
    private final static long DEFAULT_RATE = 100;
    private final static int DEFAULT_MIN = 10000;
    private final static int DEFAULT_MAX = 100000;
    private static long currentKey = 0;

    // 通过注解非设置定时任务
    @Scheduled(fixedRate = DEFAULT_RATE)
    public void sendRandomIntToKafka() {
        int randomInt= ThreadLocalRandom.current().nextInt(DEFAULT_MIN, DEFAULT_MAX);;
        produceMessage(randomInt);
    }

    public void produceMessage(int number) {
        // 配置 Kafka Properties
        Properties kafkaProp = new Properties();
        kafkaProp.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        kafkaProp.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        kafkaProp.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        Producer<String, String> producer = new KafkaProducer<>(kafkaProp);

        // 生成事件并通过 Producer 进行投递
        ProducerRecord<String, String> theRecord = new ProducerRecord<>(TOPIC_NAME, String.valueOf(currentKey), String.valueOf(number));
        currentKey++;
        producer.send(theRecord);

        producer.flush();
        producer.close();
    }
}
