package com.softuni.repository;

import com.softuni.model.entity.Offer;
import com.softuni.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
    List<Offer> findByReceiver(User receiver);
}
