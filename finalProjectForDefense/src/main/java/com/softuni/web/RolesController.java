package com.softuni.web;

import com.softuni.model.binding.RoleAddBindingModel;
import com.softuni.service.RoleService;
import com.softuni.service.UserService;
import com.softuni.web.annotation.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/roles")
public class RolesController {
    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RolesController(RoleService roleService, UserService userService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PageTitle("Roles Manager")
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    public ModelAndView add(ModelAndView modelAndView, Principal principal) {


        modelAndView.addObject("usernames", this.userService.findAllUsernames(principal.getName()));
        modelAndView.setViewName("users/role-add");

        return modelAndView;
    }

    @PostMapping("/add")
    public String addConfirm(@ModelAttribute("roleAddBindingModel")
                                     RoleAddBindingModel roleAddBindingModel) {

        this.userService.addRoleToUser(roleAddBindingModel.getUsername(), roleAddBindingModel.getRole());

        return "redirect:/";
    }
}
