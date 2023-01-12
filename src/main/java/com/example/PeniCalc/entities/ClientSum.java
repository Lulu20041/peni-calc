package com.example.PeniCalc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

@Entity
@Table(name = "clientSum")
public class ClientSumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @PastOrPresent
    @Temporal(TemporalType.DATE)
    private Date date;

    @Min(0)
    private double sum;
    @OneToOne
    @JoinColumn(name = "currencyId", referencedColumnName = "id")
    private CurrencyEntity currency;

    public ClientSumEntity() { }

    public ClientSumEntity(Date date, double sum, CurrencyEntity currency) {
        this.date = date;
        this.sum = sum;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public CurrencyEntity getCurrency() {
        return currency;
    }
}
