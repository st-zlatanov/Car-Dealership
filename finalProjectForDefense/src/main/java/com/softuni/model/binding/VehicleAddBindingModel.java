package com.softuni.model.binding;

import com.softuni.model.entity.CategoryName;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.LocalDate;

public class VehicleAddBindingModel {
    private String make;
    private String model;
    private BigDecimal price;
    private LocalDate yearOfManufacturing;
    private String description;
    private CategoryName category;
    private String imgUrl;


    public VehicleAddBindingModel() {
    }

    @Length(min = 1, max=25 , message = "Make length must be between 1 and 25 characters!")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Length(min = 1, max=25 , message = "Model length must be between 1 and 25 characters!")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Length(min = 5, message = "Description min length must be minimum 5(inclusive) characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date cannot be in the future")
    @NotNull
    public LocalDate getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(LocalDate yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    @DecimalMin(value = "0", message = "Price must be a positive number!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @NotNull(message = "Enter valid category name!")
    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    @Length(min = 1, message = "Enter valid image url!")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
