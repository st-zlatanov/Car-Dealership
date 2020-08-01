package com.softuni.web;

import com.softuni.model.binding.VehicleAddBindingModel;
import com.softuni.model.service.VehicleServiceModel;
import com.softuni.service.PartService;
import com.softuni.service.VehicleService;
import com.softuni.web.annotation.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;
    private final PartService partService;

    public VehicleController(VehicleService vehicleService, ModelMapper modelMapper, PartService partService) {
        this.vehicleService = vehicleService;
        this.modelMapper = modelMapper;
        this.partService = partService;
    }


    @GetMapping("/add")
    @PageTitle("Add Vehicle")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView add(ModelAndView modelAndView, Model model) {
        if (!model.containsAttribute("vehicleAddBindingModel")) {
            model.addAttribute("vehicleAddBindingModel", new VehicleAddBindingModel());
        }
        modelAndView.setViewName("vehicle-add");


        return modelAndView;
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("vehicleAddBindingModel") VehicleAddBindingModel vehicleAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("vehicleAddBindingModel", vehicleAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.vehicleAddBindingModel", bindingResult);
            return "redirect:add";
        }

        this.vehicleService.addVehicle(this.modelMapper
                .map(vehicleAddBindingModel, VehicleServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/details")
    @PageTitle("Car Details")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(ModelAndView modelAndView, @RequestParam("id") String id) {
        modelAndView.addObject("vehicle", this.vehicleService.findVehicleById(id));

        modelAndView.setViewName("vehicle-details");


        return modelAndView;
    }

    @GetMapping("/viewAll")
    @PageTitle("All Cars")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView viewAll(ModelAndView modelAndView) {
        modelAndView.addObject("vehicles", this.vehicleService.findAllVehicles());

        modelAndView.setViewName("vehicle-viewAll");


        return modelAndView;
    }


}
