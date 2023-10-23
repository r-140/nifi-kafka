package com.gl.bigdataprocamp.iu.kafka.consumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.bigdataprocamp.iu.kafka.consumer.dto.BitStampTrn;

public class JsonConverter {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static BitStampTrn convertRecordToDto(String jsonStr) throws JsonProcessingException {
        return mapper.readValue(jsonStr, BitStampTrn.class);
    }
}
