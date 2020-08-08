package com.softuni.scheduler;

import com.softuni.service.OfferService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OfferSchedule {
    private final OfferService offerService;


    public OfferSchedule(OfferService offerService) {
        this.offerService = offerService;
    }

    @Scheduled(cron = "0 0 */12 ? * *")
    public void clearOffers(){
        this.offerService.clearOffers();
    }
}
