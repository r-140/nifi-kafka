package com.gl.bigdataprocamp.iu.kafka.consumer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gl.bigdataprocamp.iu.kafka.consumer.dto.BitStampData;
import com.gl.bigdataprocamp.iu.kafka.consumer.dto.BitStampTrn;
import org.junit.Test;

import static com.gl.bigdataprocamp.iu.kafka.consumer.util.JsonConverter.convertRecordToDto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonConverterTest {
    private final String JSON_STR = "{\"data\":\n" +
            "{\"id\":1675829174374400,\n" +
            "\"id_str\":\"1675829174374400\",\n" +
            "\"order_type\":1,\n" +
            "\"datetime\":\"1697972957\",\n" +
            "\"microtimestamp\":\"1697972956682000\",\n" +
            "\"amount\":0.01671375,\n" +
            "\"amount_str\":\"0.01671375\",\n" +
            "\"amount_traded\":\"0\",\n" +
            "\"amount_at_create\":\"0.01671375\",\n" +
            "\"price\":29919,\n" +
            "\"price_str\":\"29919\"}\n" +
            ",\n" +
            "\"channel\":\"live_orders_btcusd\",\n" +
            "\"event\":\"order_created\"\n" +
            "}";


    @Test
    public void convertJsonToBitStampTest() throws JsonProcessingException {
        final BitStampTrn actual = convertRecordToDto(JSON_STR);
        assertNotNull(actual);
        assertNotNull(actual.getData());
        assertEquals(Double.valueOf(29919), actual.getData().getPrice());
        assertEquals(Double.valueOf(0.01671375), actual.getData().getAmount());
        assertEquals("live_orders_btcusd", actual.getChannel());
        assertEquals("order_created", actual.getEvent());
    }
}
