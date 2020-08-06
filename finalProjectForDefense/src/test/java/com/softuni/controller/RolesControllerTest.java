package com.softuni.controller;

import com.softuni.base.ControllerTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class RolesControllerTest extends ControllerTestBase {
    @Test
    @WithMockUser(username = "Stiliyan", authorities = {"ADMIN"})
    public void rolesAdd_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/roles/add"))
                .andExpect(view().name("role-add"))
                .andExpect(model().attributeExists("usernames"));
    }


}
