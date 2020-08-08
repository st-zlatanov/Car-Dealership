package com.softuni.web;

import com.softuni.model.binding.OfferAddBindingModel;
import com.softuni.model.entity.Offer;
import com.softuni.model.service.OfferServiceModel;
import com.softuni.service.AuthenticatedUserService;
import com.softuni.service.OfferService;
import com.softuni.service.UserService;
import com.softuni.service.VehicleService;
import com.softuni.web.annotation.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/offers")
public class OfferController {
    private final AuthenticatedUserService authenticatedUserService;
    private final ModelMapper modelMapper;
    private final OfferService offerService;
    private final VehicleService vehicleService;


    public OfferController(AuthenticatedUserService authenticatedUserService, ModelMapper modelMapper, OfferService offerService, UserService userService, VehicleService vehicleService) {
        this.authenticatedUserService = authenticatedUserService;
        this.modelMapper = modelMapper;
        this.offerService = offerService;

        this.vehicleService = vehicleService;
    }

    @GetMapping("/create")
    @PageTitle("Create Offer")
    public ModelAndView create(ModelAndView modelAndView, Model model, HttpSession httpSession){

        if(!model.containsAttribute("offerAddBindingModel")){
            model.addAttribute("offerAddBindingModel", new OfferAddBindingModel());
        }
        modelAndView.addObject("sender", this.authenticatedUserService.getUsername());
        modelAndView.addObject("receiver", httpSession.getAttribute("receiver"));
        modelAndView.addObject("price", httpSession.getAttribute("price"));
        modelAndView.addObject("vehicleId", httpSession.getAttribute("vehicleId"));
        modelAndView.setViewName("offers/offer-create");

        return modelAndView;
    }
    @PostMapping("/create")
    @PageTitle("Create Offer")
    public String createConfirm(@Valid @ModelAttribute("offerAddBindingModel") OfferAddBindingModel offerAddBindingModel,
                                      BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindingModel", bindingResult);
            return "redirect:create";
        }

        this.offerService.addOffer(this.modelMapper
                .map(offerAddBindingModel, OfferServiceModel.class));
        return "redirect:/";
    }
    @GetMapping("/view")
    @PageTitle("View Offers")
    public ModelAndView view(ModelAndView modelAndView){

        modelAndView.addObject("offers", this.offerService.getAllOffersForUser(this.authenticatedUserService.getUsername()));
        modelAndView.setViewName("offers/offers-view");

        return modelAndView;
    }
    @GetMapping("/accept/{id}")
    @PageTitle("Accepted Offer")
    public ModelAndView acceptOffer(ModelAndView modelAndView, @PathVariable("id")String id){
        Offer offer = this.offerService.findOfferById(id);
        this.offerService.deleteOffer(id);
        this.vehicleService.deleteVehicleById(offer.getVehicle().getId());
        modelAndView.addObject("offers", this.offerService.getAllOffersForUser(this.authenticatedUserService.getUsername()));
        modelAndView.setViewName("offers/offers-view");

        return modelAndView;
    }

    @GetMapping("/reject/{id}")
    @PageTitle("Accepted Offer")
    public ModelAndView rejectOffer(ModelAndView modelAndView, @PathVariable("id")String id){
        this.offerService.deleteOffer(id);

        modelAndView.addObject("offers", this.offerService.getAllOffersForUser(this.authenticatedUserService.getUsername()));
        modelAndView.setViewName("offers/offers-view");

        return modelAndView;
    }




}
