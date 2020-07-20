package com.softuni.web;

import com.softuni.model.binding.PartAddBindingModel;
import com.softuni.model.binding.VehicleAddBindingModel;
import com.softuni.model.service.PartServiceModel;
import com.softuni.model.service.VehicleServiceModel;
import com.softuni.service.PartService;
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
@RequestMapping("/parts")
public class PartController {
    private final PartService partService;
    private final ModelMapper modelMapper;

    public PartController(PartService partService, ModelMapper modelMapper) {
        this.partService = partService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public ModelAndView add(HttpSession httpSession, ModelAndView modelAndView, Model model){
        if (httpSession.getAttribute("user") == null) {
            modelAndView.setViewName("index");
        } else {
            if(!model.containsAttribute("partAddBindingModel")){
                model.addAttribute("partAddBindingModel", new PartAddBindingModel());
            }
            modelAndView.setViewName("part-add");
        }

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
}
