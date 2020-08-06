package com.softuni.controller;

import com.softuni.base.ControllerTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class OfferControllerTest extends ControllerTestBase {
    @Test
    @WithMockUser(username = "Stiliyan")
    public void offerCreate_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/offers/create"))
                .andExpect(view().name("offer-create"))
                .andExpect(model().attributeExists("offerAddBindingModel", "sender"));
    }
    @Test
    @WithMockUser(username = "Stiliyan")
    public void offerView_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/offers/view"))
                .andExpect(view().name("offers-view"))
                .andExpect(model().attributeExists("offers"));
    }
}
