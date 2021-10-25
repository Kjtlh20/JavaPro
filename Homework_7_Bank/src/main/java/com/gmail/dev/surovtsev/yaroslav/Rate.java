package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "currency_rates")
public class Rate {
    @Id
    @GeneratedValue
    private Long id;

    private Date period;

    @ManyToOne
    @JoinColumn(name = "currency_from_id")
    private Currency currencyFrom;

    @ManyToOne
    @JoinColumn(name = "currency_to_id")
    private Currency currencyTo;

    @Column(nullable = false)
    private Double rate;

    public Rate() {
    }

    public Rate(Date period, Currency currencyFrom, Currency currencyTo, Double rate) {
        this.period = period;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(Currency currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public Currency getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(Currency currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", period=" + period +
                ", currencyFrom=" + currencyFrom +
                ", currencyTo=" + currencyTo +
                ", rate=" + rate +
                '}';
    }
}
