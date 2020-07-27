package com.softuni.web;

import com.softuni.model.view.ProfileViewModel;
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
import com.softuni.model.binding.UserLoginBindingModel;
import com.softuni.model.binding.UserRegisterBindingModel;
import com.softuni.model.service.UserServiceModel;
import com.softuni.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model) {
        if(!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
     return "login";
    }
    @PostMapping("/login")
    public String loginConfirm(@Valid
                                   @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession){
    if(bindingResult.hasErrors()){

        redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
        return "redirect:login";
    }
    UserServiceModel user = this.userService.findByUsername(userLoginBindingModel.getUsername());
    if(user == null || !user.getPassword().equals(userLoginBindingModel.getPassword())){
        redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
        redirectAttributes.addFlashAttribute("notFound", true );
        return "redirect:login";
    }

    httpSession.setAttribute("user", user);
    return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){
        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid
                                      @ModelAttribute("userRegisterBindingModel")UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword()) || userRegisterBindingModel.getEmail().equals("")){

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }
        this.userService.register(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/profile")
    public ModelAndView profile(ModelAndView modelAndView, Principal principal){
        UserServiceModel userServiceModel = this.userService.findByUsername(principal.getName());
        ProfileViewModel profileViewModel = this.modelMapper.map(userServiceModel, ProfileViewModel.class);
        modelAndView.addObject("model", profileViewModel);
     //   modelAndView.addObject("auth", this.userService.findAuthorities(principal.getName()));
        modelAndView.addObject("auth", profileViewModel.getAuthorities());
        modelAndView.addObject("admin", false);
        if(profileViewModel.getAuthorities().size()>1){
            modelAndView.addObject("admin", true);
        }
        modelAndView.setViewName("profile");
        return modelAndView;
    }

}
