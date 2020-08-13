package com.softuni.controller;

import com.softuni.base.ControllerTestBase;
import com.softuni.model.binding.RoleAddBindingModel;
import com.softuni.model.binding.UserRegisterBindingModel;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RolesControllerTest extends ControllerTestBase {
    @Test
    @WithMockUser(username = "Stiliyan", authorities = {"ADMIN"})
    public void rolesAdd_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/roles/add"))
                .andExpect(view().name("users/role-add"))
                .andExpect(model().attributeExists("usernames"));
    }

    @Test
    @WithMockUser(username = "Stiliyan", authorities = {"ADMIN"})
    public void rolesAddPost_shouldRedirect() throws Exception {
        RoleAddBindingModel model = new RoleAddBindingModel();
        model.setRole("ADMIN");
        model.setUsername("stilkata");

        this.mockMvc
                .perform(post("/roles/add")
                        .flashAttr("roleAddBindingModel", model))
                .andExpect(status().is3xxRedirection());
    }


}
