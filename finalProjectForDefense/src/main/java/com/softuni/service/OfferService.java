package com.softuni.service;

import com.softuni.model.entity.Offer;
import com.softuni.model.service.OfferServiceModel;

import java.util.List;

public interface OfferService {
    void addOffer(OfferServiceModel offerServiceModel);
    List<Offer> getAllOffersForUser(String id);
    void clearOffers();
    void deleteOffer(String id);
    Offer findOfferById(String id);
}
