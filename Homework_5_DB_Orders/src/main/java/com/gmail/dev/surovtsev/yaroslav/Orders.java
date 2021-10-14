package com.gmail.dev.surovtsev.yaroslav;

import java.util.Date;

public class Orders {
    @Id
    private int id;

    private int number;
    private Date date;
    private int customerId;

    public Orders() {
    }

    public Orders(int number, Date date, int customerId) {
        this.number = number;
        this.date = date;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", number=" + number +
                ", date=" + date +
                ", customerId=" + customerId +
                '}';
    }
}
