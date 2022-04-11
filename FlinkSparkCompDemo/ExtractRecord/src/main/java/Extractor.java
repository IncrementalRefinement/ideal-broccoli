import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Properties;

public class Extractor {

    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    // private final static String TOPIC_NAME = "spark_flink_comp_topic_test";
    private final static String TOPIC_NAME = "spark_flink_comp_topic_test_output";

    public static void main(String[] args) throws IOException {

        PrintWriter writer = new PrintWriter(TOPIC_NAME + ".csv", StandardCharsets.UTF_8);

        Properties kafkaProp = new Properties();
        kafkaProp.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        kafkaProp.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProp.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaProp.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        kafkaProp.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        kafkaProp.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-extract");

        kafkaProp.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Consumer<String, String> consumer = new KafkaConsumer<>(kafkaProp);

        consumer.subscribe(Collections.singletonList(TOPIC_NAME));
        consumer.poll(0);
        consumer.seekToBeginning(consumer.assignment());
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            if (records.isEmpty()) {
                break;
            }
            for (ConsumerRecord<String, String> record : records) {
                writer.println(record.key()  + ";" + record.value() + ";" + record.timestamp());
            }
        }
        writer.close();
    }
}
