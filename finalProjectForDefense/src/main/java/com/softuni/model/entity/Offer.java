package com.softuni.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="offers")
public class Offer extends BaseEntity{
    private User sender;
    private User receiver;
    private String text;
    private BigDecimal price;
    private Vehicle vehicle;


    public Offer() {
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="sender_id", referencedColumnName = "id")
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="receiver_id", referencedColumnName = "id")
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Column(name="text", nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name="price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @OneToOne
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
