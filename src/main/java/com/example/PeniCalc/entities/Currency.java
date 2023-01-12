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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getOfficialRate() {
        return officialRate;
    }
}
