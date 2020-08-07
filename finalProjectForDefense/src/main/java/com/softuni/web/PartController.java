package com.softuni.web;

import com.softuni.model.binding.PartAddBindingModel;
import com.softuni.model.binding.VehicleAddBindingModel;
import com.softuni.model.service.PartServiceModel;
import com.softuni.model.service.VehicleServiceModel;
import com.softuni.service.PartService;
import com.softuni.web.annotation.PageTitle;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/parts")
public class PartController {
    private final PartService partService;
    private final ModelMapper modelMapper;

    public PartController(PartService partService, ModelMapper modelMapper) {
        this.partService = partService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PageTitle("Add Part")
    public ModelAndView add(ModelAndView modelAndView, Model model) {

        if (!model.containsAttribute("partAddBindingModel")) {
            model.addAttribute("partAddBindingModel", new PartAddBindingModel());
        }
        modelAndView.setViewName("part-add");


        return modelAndView;
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("partAddBindingModel") PartAddBindingModel partAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
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
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/viewAll")
    @PageTitle("All Cars")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView viewAll(ModelAndView modelAndView) {
        modelAndView.addObject("parts", this.partService.findAllParts());

        modelAndView.setViewName("parts-viewAll");


        return modelAndView;
    }

}
