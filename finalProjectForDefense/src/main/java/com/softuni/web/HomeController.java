package com.softuni.web;

import com.softuni.service.PartService;
import com.softuni.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {
    private final VehicleService vehicleService;
    private final PartService partService;

    public HomeController(VehicleService vehicleService, PartService partService) {
        this.vehicleService = vehicleService;
        this.partService = partService;
    }

    @GetMapping("/")
    public ModelAndView index(Principal principal, ModelAndView modelAndView) {
        if (principal == null) {
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("vehicles", this.vehicleService.findAllVehicles());
            modelAndView.addObject("parts", this.partService.findAllParts());
            modelAndView.setViewName("home");
        }

        return modelAndView;
    }
}
