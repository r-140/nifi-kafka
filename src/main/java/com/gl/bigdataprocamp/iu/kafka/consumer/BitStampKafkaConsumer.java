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

//        to enable at most once strategy just set autocommit to true and comment out following properties

//        configuring at least once semantic
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // Make Auto commit interval to a big number so that auto commit does not happen,
        // we are going to control the offset commit via consumer.commitSync(); after processing             // message.
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "999999999999");

        // This is how to control number of messages being read in each poll
        properties.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "135");

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Collections.singletonList(topic));

        // poll for new data
        while(true){
            ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> record : records){
                log.info("Key: " + record.key() + ", Value: " + record.value());
                log.info("Partition: " + record.partition() + ", Offset:" + record.offset());
            }

//            at least once strategy
//             Below call is important to control the offset commit. Do this call after you
//             finish processing the business process.
            consumer.commitSync();
        }

    }
}
