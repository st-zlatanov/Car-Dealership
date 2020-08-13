package com.softuni.controller;

import com.softuni.base.ControllerTestBase;
import com.softuni.model.binding.OfferAddBindingModel;
import com.softuni.model.binding.UserRegisterBindingModel;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OfferControllerTest extends ControllerTestBase {
    @Test
    @WithMockUser(username = "Stiliyan")
    public void offerCreate_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/offers/create"))
                .andExpect(view().name("offers/offer-create"))
                .andExpect(model().attributeExists("offerAddBindingModel", "sender"));
    }
    @Test
    @WithMockUser(username = "Stiliyan")
    public void OfferAddPost_shouldReturnCorrectView() throws Exception {
        OfferAddBindingModel model = new OfferAddBindingModel();
        model.setReceiver("stilkata");
        model.setSender("stilkata123");
        model.setText("stilkataasdfg");
        model.setVehicleId("12345");
        model.setPrice(BigDecimal.ONE);

        this.mockMvc
                .perform(post("/offers/create")
                        .flashAttr("offerAddBindingModel", model))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "Stiliyan")
    public void offerView_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/offers/view"))
                .andExpect(view().name("offers/offers-view"))
                .andExpect(model().attributeExists("offers"));
    }
}
