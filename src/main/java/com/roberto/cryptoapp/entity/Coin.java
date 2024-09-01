package com.roberto.cryptoapp.entity;

import java.math.BigDecimal;
import java.util.Timer;

public class Coin {

    private int id;
    private String name;
    private BigDecimal price;
    private BigDecimal quantity;
    private Timer datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Timer getDatetime() {
        return datetime;
    }

    public void setDatetime(Timer datetime) {
        this.datetime = datetime;
    }
}
