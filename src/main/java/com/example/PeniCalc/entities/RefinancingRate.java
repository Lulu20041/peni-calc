package com.example.PeniCalc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PastOrPresent;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "refinancingRate")
public class RefinancingRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Max(1)
    private double rate;
    @PastOrPresent
    private Date beginDate;
    private Date endDate;
    public RefinancingRate() { }
    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getRate() {
        return rate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
