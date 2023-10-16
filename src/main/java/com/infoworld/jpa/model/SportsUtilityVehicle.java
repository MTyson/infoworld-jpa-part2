package com.infoworld.jpa.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "SUV")
//@DiscriminatorValue("SUV")
public class SportsUtilityVehicle extends Car {
    private Boolean thirdRow;
    private Integer towingCapacity;

    public SportsUtilityVehicle() {
    }

    public SportsUtilityVehicle(String model, String make, Integer year, Boolean thirdRow, Integer towingCapacity) {
        super(model, make, year);
        this.thirdRow = thirdRow;
        this.towingCapacity = towingCapacity;
    }

    public Boolean getThirdRow() {
        return thirdRow;
    }

    public void setThirdRow(Boolean thirdRow) {
        this.thirdRow = thirdRow;
    }

    public Integer getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(Integer towingCapacity) {
        this.towingCapacity = towingCapacity;
    }
}
