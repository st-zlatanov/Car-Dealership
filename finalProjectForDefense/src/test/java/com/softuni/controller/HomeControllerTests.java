package com.softuni.controller;

import com.softuni.base.ControllerTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTests extends ControllerTestBase {


    @Test
    public void index_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(view().name("index"));
    }
    @Test
    @WithMockUser(username = "Stiliyan")
    public void home_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("vehicles", "parts"));
    }
}
