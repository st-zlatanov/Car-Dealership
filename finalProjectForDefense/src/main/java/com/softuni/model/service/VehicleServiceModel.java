package com.softuni.model.service;

import com.softuni.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class VehicleServiceModel extends BaseServiceModel {
    private CategoryServiceModel category;
    private String make;
    private String model;
    private BigDecimal price;
    private LocalDate yearOfManufacturing;
    private String description;
    private String imgUrl;
    private UserServiceModel owner;

    public VehicleServiceModel() {
    }

    public CategoryServiceModel getCategory() {
        return category;
    }

    public void setCategory(CategoryServiceModel category) {
        this.category = category;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(LocalDate yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public UserServiceModel getOwner() {
        return owner;
    }

    public void setOwner(UserServiceModel owner) {
        this.owner = owner;
    }
}
