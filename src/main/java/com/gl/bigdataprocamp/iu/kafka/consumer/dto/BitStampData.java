package com.gl.bigdataprocamp.iu.kafka.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BitStampData {

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

    public BitStampData() {
    }

    public BitStampData(Long id, int orderType, Double amount, Double amountTraded, Double amountAtCreate, Double price) {
        this.id = id;
        this.orderType = orderType;
        this.amount = amount;
        this.amountTraded = amountTraded;
        this.amountAtCreate = amountAtCreate;
        this.price = price;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitStampData bitstampData = (BitStampData) o;
        return orderType == bitstampData.orderType && Objects.equals(id, bitstampData.id)
                && Objects.equals(amount, bitstampData.amount)
                && Objects.equals(amountTraded, bitstampData.amountTraded)
                && Objects.equals(amountAtCreate, bitstampData.amountAtCreate)
                && Objects.equals(price, bitstampData.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderType, amount, amountTraded, amountAtCreate, price);
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
                '}';
    }
}
