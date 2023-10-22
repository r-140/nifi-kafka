package com.gl.bigdataprocamp.iu.kafka.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bitstamp {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("order_type")
    private int orderType;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("amount_traded")
    private Double amountTraded;

    @JsonProperty("amount_at_create")
    private Double amountAtCreate;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("channel")
    private String channel;

    @JsonProperty("event")
    private String event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountTraded() {
        return amountTraded;
    }

    public void setAmountTraded(Double amountTraded) {
        this.amountTraded = amountTraded;
    }

    public Double getAmountAtCreate() {
        return amountAtCreate;
    }

    public void setAmountAtCreate(Double amountAtCreate) {
        this.amountAtCreate = amountAtCreate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        Bitstamp bitstamp = (Bitstamp) o;
        return orderType == bitstamp.orderType && Objects.equals(id, bitstamp.id) && Objects.equals(amount, bitstamp.amount) && Objects.equals(amountTraded, bitstamp.amountTraded) && Objects.equals(amountAtCreate, bitstamp.amountAtCreate) && Objects.equals(price, bitstamp.price) && Objects.equals(channel, bitstamp.channel) && Objects.equals(event, bitstamp.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderType, amount, amountTraded, amountAtCreate, price, channel, event);
    }

    @Override
    public String toString() {
        return "Bitstamp{" +
                "id=" + id +
                ", orderType=" + orderType +
                ", amount=" + amount +
                ", amountTraded=" + amountTraded +
                ", amountAtCreate=" + amountAtCreate +
                ", price=" + price +
                ", channel='" + channel + '\'' +
                ", event='" + event + '\'' +
                '}';
    }
}
