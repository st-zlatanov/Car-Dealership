package com.softuni.web;

import com.softuni.model.binding.VehicleAddBindingModel;
import com.softuni.model.service.VehicleServiceModel;
import com.softuni.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;

    public VehicleController(VehicleService vehicleService, ModelMapper modelMapper) {
        this.vehicleService = vehicleService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public ModelAndView add(HttpSession httpSession, ModelAndView modelAndView, Model model){
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
            if(!model.containsAttribute("vehicleAddBindingModel")){
                model.addAttribute("vehicleAddBindingModel", new VehicleAddBindingModel());
            }
            modelAndView.setViewName("vehicle-add");
        }

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
}
