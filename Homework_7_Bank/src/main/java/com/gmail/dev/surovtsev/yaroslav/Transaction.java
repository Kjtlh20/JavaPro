package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Date period;

    @ManyToOne
    @JoinColumn(name = "account_from_id")
    private Account accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_to_id")
    private Account accountTo;

    @Column(nullable = false)
    private Double amount;

    public Transaction() {
    }

    public Transaction(Date period, Account accountFrom, Account accountTo, Double amount) {
        this.period = period;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
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

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", period=" + period +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", amount=" + amount +
                '}';
    }
}
