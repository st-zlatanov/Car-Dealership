package com.softuni.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vehicles")
public class Vehicle extends BaseEntity {
    private VehicleType type;
    private String make;
    private String model;
    private BigDecimal price;
    private LocalDate yearOfManufacturing;
    private String description;

    public Vehicle() {
    }

    @Enumerated
    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    @Column(name="make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name="model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name="price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name="yearOfManufacturing")
    public LocalDate getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(LocalDate yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    @Column(name="description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
