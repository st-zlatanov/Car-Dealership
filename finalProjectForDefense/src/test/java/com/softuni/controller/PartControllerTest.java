package com.softuni.controller;

import com.softuni.base.ControllerTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class PartControllerTest extends ControllerTestBase {
    @Test
    @WithMockUser(username = "Stiliyan")
    public void partsAdd_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/parts/add"))
                .andExpect(view().name("parts/part-add"))
                .andExpect(model().attributeExists("partAddBindingModel"));
    }
    @Test
    @WithMockUser(username = "Stiliyan")
    public void partsViewAll_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/parts/viewAll"))
                .andExpect(view().name("parts/parts-viewAll"))
                .andExpect(model().attributeExists("parts"));
    }

}
