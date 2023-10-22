package com.gl.bigdataprocamp.iu.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Logger;

public class BitStampKafkaConsumer {
    final static Logger log = Logger.getLogger("BitStampKafkaConsumer");
    public static void main(String[] args) {
        log.info("starting consuming massages");
        System.out.println("****starting consuming****");

        String bootstrapServers = "127.0.0.1:9092";
        String groupId = "bitstamp-trn-group";
        String topic = "bitstamp-trn-topic";

        // create consumer configs
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList(topic));

        // poll for new data
        while(true){
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));

            System.out.println("number of records " + records.count());

            for (ConsumerRecord<String, String> record : records){
                log.info("Key: " + record.key() + ", Value: " + record.value());
                System.out.println("Key: " + record.key() + ", Value: " + record.value());
                log.info("Partition: " + record.partition() + ", Offset:" + record.offset());
                System.out.println("Partition: " + record.partition() + ", Offset:" + record.offset());
            }
        }

    }
}
