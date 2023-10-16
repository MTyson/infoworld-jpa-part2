package com.infoworld.jpa.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "SPORTSCAR")
//@DiscriminatorValue("SportsCar")
public class SportsCar extends Car {
    private Integer topSpeed;
    private Double zeroToSixty;

    public SportsCar() {
    }

    public SportsCar(String model, String make, Integer year, Integer topSpeed, Double zeroToSixty) {
        super(model, make, year);
        this.topSpeed = topSpeed;
        this.zeroToSixty = zeroToSixty;
    }

    public Integer getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(Integer topSpeed) {
        this.topSpeed = topSpeed;
    }

    public Double getZeroToSixty() {
        return zeroToSixty;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }
}
