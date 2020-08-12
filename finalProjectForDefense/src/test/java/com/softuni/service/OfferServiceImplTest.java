package com.softuni.service;

import com.softuni.base.TestBase;
import com.softuni.model.entity.Offer;
import com.softuni.model.entity.User;
import com.softuni.model.service.OfferServiceModel;
import com.softuni.model.service.UserServiceModel;
import com.softuni.repository.OfferRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class OfferServiceImplTest extends TestBase {
    private List<Offer> offersList;
    @MockBean
    OfferRepository offerRepository;

    @Autowired
    OfferService offerService;
    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    public void setupTest() {
        offersList = new ArrayList<>();
        when(offerRepository.findAll())
                .thenReturn(offersList);
    }

    @Test
    void addingOffer_shouldAddCorrectly() {
        offersList.clear();

        Offer offer = new Offer();
        offer.setText("newoffer");

        offersList.add(offer);
        OfferServiceModel model = modelMapper.map(offer, OfferServiceModel.class);
        model.setText("asdfghj");

        when(offerRepository.count()).thenReturn(1L);
        assertEquals(offersList.size(), offerRepository.count());
    }

    @Test
    void findById_whenIdDoesntExist_shouldThrowException() {
        offersList.clear();

        Offer offer = new Offer();

        offersList.add(offer);
        assertThrows(Exception.class,
                () -> offerService.findOfferById(""));
    }
}