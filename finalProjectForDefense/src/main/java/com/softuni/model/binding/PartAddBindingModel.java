package com.softuni.model.binding;

import com.softuni.model.entity.Category;
import com.softuni.model.entity.CategoryName;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PartAddBindingModel {
    private String name;
    private String carModel;
    private BigDecimal price;
    private String description;
    private String condition;
    private MultipartFile image;

    private CategoryName category;

    public PartAddBindingModel() {
    }

    @Length(min = 1, max=25 , message = "Name length must be between 1 and 25 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max=35 , message = "Car model length must be between 1 and 35 characters!")
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @DecimalMin(value = "0", message = "Price must be a positive number!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Length(min = 5, message = "Description min length must be minimum 5(inclusive) characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Length(min = 1, max=35 , message = "Condition length must be between 1 and 35 characters!")
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }


    @NotNull(message = "Enter valid category name!")
    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    @NotNull
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
