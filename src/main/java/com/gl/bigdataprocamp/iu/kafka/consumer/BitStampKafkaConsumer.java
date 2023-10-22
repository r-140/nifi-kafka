package com.gl.bigdataprocamp.iu.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.bigdataprocamp.iu.kafka.consumer.dto.Bitstamp;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Logger;

import static com.gl.bigdataprocamp.iu.kafka.consumer.config.ConsumerConfigHelper.getConsumerConfig;

public class BitStampKafkaConsumer {
    private final static Logger log = Logger.getLogger("BitStampKafkaConsumer");

    //    todo pass topic as params
    private static String topic = "bitstamp-trn-topic";

    private static Integer TIMEOUT = 100;

    public static void main(String[] args) {
        log.info("starting consuming massages");

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerConfig());

        consumer.subscribe(Collections.singletonList(topic));

        // poll for new data
        while(true){
            ConsumerRecords<String, String> records =  consumer.poll(Duration.ofMillis(TIMEOUT));

            try {
                process(records);
            } catch (JsonProcessingException e) {
                log.info("Exception has been thrown while processing the record " + e.getMessage());
                throw new RuntimeException(e);
            }
//            at least once strategy
            consumer.commitSync();
        }
    }

    private static void process(ConsumerRecords<String, String> records) throws JsonProcessingException {
        for (ConsumerRecord<String, String> record : records){
//            log.info("Key: " + record.key() + ", Value: " + record.value());

//            log.info("Partition: " + record.partition() + ", Offset:" + record.offset());

            final Bitstamp bitstamp = convertRecordToDto(record);

            log.info("Converted record " + bitstamp);
        }
    }

    private static Bitstamp convertRecordToDto(ConsumerRecord<String, String> record) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(record.value(), Bitstamp.class);
    }
}
