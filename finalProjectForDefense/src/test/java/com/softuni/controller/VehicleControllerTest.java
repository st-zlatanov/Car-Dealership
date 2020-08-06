package com.softuni.controller;

import com.softuni.base.ControllerTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class VehicleControllerTest extends ControllerTestBase {

    @Test
    @WithMockUser(username = "Stiliyan")
    public void vehiclesAdd_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/vehicles/add"))
                .andExpect(view().name("vehicle-add"))
                .andExpect(model().attributeExists("vehicleAddBindingModel"));
    }

}
