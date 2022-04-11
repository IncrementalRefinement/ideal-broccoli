package flink;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.base.DeliveryGuarantee;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.reader.deserializer.KafkaRecordDeserializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class MyFlinkRunner {

    private final static String BOOTSTRAP_SERVER = "localhost:9092";
    private final static String INPUT_TOPIC = "spark_flink_comp_topic_test";
    private final static String OUTPUT_TOPIC = "spark_flink_comp_topic_test";
    private final static String GROUP_ID = "test_group";


    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        KafkaSource<String> kafkaSource = KafkaSource.<String>builder()
                .setBootstrapServers(BOOTSTRAP_SERVER)
                .setTopics(INPUT_TOPIC)
                .setGroupId(GROUP_ID )
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setDeserializer(KafkaRecordDeserializationSchema.valueOnly(StringDeserializer.class))
                .build();

        DataStreamSource<String> inputStream = env.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "Kafka Source");

        DataStream<String> outputStream = inputStream.process(new PrimeNumberFinder());

        KafkaSink<String> sink = KafkaSink.<String>builder()
                .setBootstrapServers(BOOTSTRAP_SERVER)
                .setRecordSerializer(KafkaRecordSerializationSchema.builder()
                        .setTopic(OUTPUT_TOPIC)
                        .setValueSerializationSchema(new SimpleStringSchema())
                        .build()
                )
                .setDeliverGuarantee(DeliveryGuarantee.AT_LEAST_ONCE)
                .build();


        outputStream.sinkTo(sink);

        env.execute("Prime Number Calculation");
    }
}
