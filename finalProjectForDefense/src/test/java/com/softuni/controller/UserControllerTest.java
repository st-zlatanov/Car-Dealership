package com.softuni.controller;

import com.softuni.base.ControllerTestBase;
import com.softuni.model.binding.UserRegisterBindingModel;
import com.softuni.model.binding.VehicleAddBindingModel;
import com.softuni.model.entity.CategoryName;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends ControllerTestBase {
    @Test
    public void usersLogin_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/login"))
                .andExpect(view().name("users/login"))
                .andExpect(model().attributeExists("userLoginBindingModel"));
    }
    @Test
    public void usersRegister_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/register"))
                .andExpect(view().name("users/register"))
                .andExpect(model().attributeExists("userRegisterBindingModel"));
    }
    @Test
    public void userRegisterPost_shouldReturnCorrectView() throws Exception {
        UserRegisterBindingModel model = new UserRegisterBindingModel();
        model.setEmail("sti12311@abv.bg");
        model.setUsername("stilkata");
        model.setPassword("stilkata");
        model.setConfirmPassword("stilkata");
        this.mockMvc
                .perform(post("/users/register")
                        .flashAttr("userRegisterBindingModel", model))
                .andExpect(status().isOk());
    }
    @Test
    public void userRegisterPost_whenPasswordsDontMatch_shouldReturnCorrectView() throws Exception {
        UserRegisterBindingModel model = new UserRegisterBindingModel();
        model.setEmail("sti12311@abv.bg");
        model.setUsername("stilkata");
        model.setPassword("stilkata");
        model.setConfirmPassword("stil");
        this.mockMvc
                .perform(post("/users/register")
                        .flashAttr("userRegisterBindingModel", model))
                .andExpect(status().is3xxRedirection());

    }
    @Test
    @WithMockUser(username = "Stiliyan")
    public void usersProfile_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/profile"))
                .andExpect(view().name("users/profile"))
                .andExpect(model().attributeExists("model", "auth", "admin"));
    }
    @Test
    @WithMockUser(username = "Stiliyan", authorities = {"ADMIN"})
    public void usersViewAll_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/view"))
                .andExpect(view().name("users/view-users"))
                .andExpect(model().attributeExists("users"));
    }


}
