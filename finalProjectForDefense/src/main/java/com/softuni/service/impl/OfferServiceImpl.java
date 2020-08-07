package com.softuni.service.impl;

import com.softuni.model.entity.Offer;
import com.softuni.model.entity.User;
import com.softuni.model.entity.Vehicle;
import com.softuni.model.service.OfferServiceModel;
import com.softuni.repository.OfferRepository;
import com.softuni.service.OfferService;
import com.softuni.service.UserService;
import com.softuni.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final UserService userService;
    private final VehicleService vehicleService;

    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository, @Lazy
            UserService userService, @Lazy VehicleService vehicleService) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    @Override
    public void addOffer(OfferServiceModel offerServiceModel) {
        User userReceiver =this.modelMapper.map(this.userService.findByUsername(offerServiceModel.getReceiver()),User.class);

        User userSender =this.modelMapper.map(this.userService.findByUsername(offerServiceModel.getSender()),User.class);

        Vehicle vehicle = this.modelMapper.map(this.vehicleService.findVehicleById(offerServiceModel.getVehicleId()), Vehicle.class);
        Offer offer = this.modelMapper.map(offerServiceModel, Offer.class);

        offer.setReceiver(userReceiver);
        offer.setSender(userSender);
        offer.setVehicle(vehicle);


        this.offerRepository.saveAndFlush(offer);

    }

    @Override
    public List<Offer> getAllOffersForUser(String username) {
        User user = this.modelMapper.map(this.userService.findByUsername(username),User.class);

       return new ArrayList<>(this.offerRepository.findByReceiver(user));
    }
}
