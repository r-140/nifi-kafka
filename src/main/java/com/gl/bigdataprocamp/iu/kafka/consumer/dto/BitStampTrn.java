package com.gl.bigdataprocamp.iu.kafka.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitStampTrn {

    private BitStampData data;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("event")
    private String event;

    public BitStampTrn() {
    }

    public BitStampTrn(BitStampData data, String channel, String event) {
        this.data = data;
        this.channel = channel;
        this.event = event;
    }

    public BitStampData getData() {
        return data;
    }

    public void setData(BitStampData data) {
        this.data = data;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitStampTrn that = (BitStampTrn) o;
        return Objects.equals(data, that.data) && Objects.equals(channel, that.channel) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, channel, event);
    }

    @Override
    public String toString() {
        return "BitstampTrn{" +
                "data=" + data +
                ", channel='" + channel + '\'' +
                ", event='" + event + '\'' +
                '}';
    }
}
