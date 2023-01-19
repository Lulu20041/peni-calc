package com.example.PeniCalc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "peni")
public class Peni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @PastOrPresent
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date periodBegin;
    @PastOrPresent
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date periodEnd;
    @Min(0)
    private int amountOfDays;
    @OneToOne
    @JoinColumn(name = "refinancingRate_id", referencedColumnName = "id")
    private RefinancingRate refinancingRate;
    @Min(0)
    private double taxSum;
    @Min(0)
    private double peniAmount;
    public Peni() { }

    public Date getPeriodBegin() {
        return periodBegin;
    }

    public void setPeriodBegin(Date periodBegin) {
        this.periodBegin = periodBegin;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public double getPeniAmount() {
        return peniAmount;
    }

    public void setPeniAmount(double peniAmount) {
        this.peniAmount = peniAmount;
    }

    public double getTaxSum() {
        return taxSum;
    }

    public void setTaxSum(double taxSum) {
        this.taxSum = taxSum;
    }

    public int getAmountOfDays() {
        return amountOfDays;
    }

    public void setAmountOfDays(int amountOfDays) {
        this.amountOfDays = amountOfDays;
    }

    public RefinancingRate getRefinancingRate() {
        return refinancingRate;
    }
    public void setRefinancingRate(RefinancingRate refinancingRate) {
        this.refinancingRate = refinancingRate;
    }
}
