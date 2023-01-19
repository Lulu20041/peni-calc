package com.example.PeniCalc.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Min(0)
    private double officialRate;
    public Currency() { }
    public Currency(String name, double officialRate) {
        this.name = name;
        this.officialRate = officialRate;
    }
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

    public double getOfficialRate() {
        return officialRate;
    }

    public void setOfficialRate(double officialRate) {
        this.officialRate = officialRate;
    }
}
