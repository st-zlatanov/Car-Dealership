package com.softuni.controller;

import com.softuni.base.ControllerTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class UserControllerTest extends ControllerTestBase {
    @Test
    public void usersLogin_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/login"))
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("userLoginBindingModel"));
    }
    @Test
    public void usersRegister_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/register"))
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("userRegisterBindingModel"));
    }
    @Test
    @WithMockUser(username = "Stiliyan")
    public void usersProfile_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/profile"))
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("model", "auth", "admin"));
    }
    @Test
    @WithMockUser(username = "Stiliyan", authorities = {"ADMIN"})
    public void usersViewAll_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/view"))
                .andExpect(view().name("view-users"))
                .andExpect(model().attributeExists("users"));
    }


}
