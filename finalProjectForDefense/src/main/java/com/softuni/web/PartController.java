package com.softuni.web;

import com.softuni.model.binding.PartAddBindingModel;
import com.softuni.model.service.PartServiceModel;
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
import java.io.IOException;

@Controller
@RequestMapping("/parts")
public class PartController {
    private final PartService partService;
    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;

    public PartController(PartService partService, VehicleService vehicleService, ModelMapper modelMapper) {
        this.partService = partService;
        this.vehicleService = vehicleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PageTitle("Add Part")
    public ModelAndView add(ModelAndView modelAndView, Model model) {

        if (!model.containsAttribute("partAddBindingModel")) {
            model.addAttribute("partAddBindingModel", new PartAddBindingModel());
        }
        modelAndView.setViewName("parts/part-add");


        return modelAndView;
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("partAddBindingModel") PartAddBindingModel partAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("partAddBindingModel", partAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.partAddBindingModel", bindingResult);
            return "redirect:add";
        }

        this.partService.addPart(this.modelMapper
                .map(partAddBindingModel, PartServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public ModelAndView delete(ModelAndView modelAndView,@PathVariable("id")String id){
        this.partService.delete(id);
        modelAndView.addObject("vehicles", this.vehicleService.findAllVehicles());
        modelAndView.addObject("parts", this.partService.findAllParts());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/viewAll")
    @PageTitle("All Parts")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView viewAll(ModelAndView modelAndView) {
        modelAndView.addObject("parts", this.partService.findAllParts());

        modelAndView.setViewName("parts/parts-viewAll");


        return modelAndView;
    }

}
