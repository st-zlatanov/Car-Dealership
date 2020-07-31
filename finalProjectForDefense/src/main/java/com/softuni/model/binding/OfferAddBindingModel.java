package com.softuni.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class OfferAddBindingModel {
    private String sender;
    private String receiver;
    private String text;
    private BigDecimal price;


    public OfferAddBindingModel() {
    }

    @Length(min = 1, max=25 , message = "Sender length must be between 1 and 25 characters!")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Length(min = 1, max=25 , message = "Receiver length must be between 1 and 25 characters!")
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Length(min = 5, message = "Text length must be at least 5!")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @DecimalMin(value = "0", message = "Price must be positive number!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
