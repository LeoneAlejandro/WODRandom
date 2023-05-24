package com.aleleone.WOD.Randomizer.presentation.controller;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.aleleone.WOD.Randomizer.WodRandomizerApplication;


@SpringBootTest(
        classes = WodRandomizerApplication.class,
        webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class JwtAuthenticationControllerTest {

    @Autowired
    private MockMvc mvc;
	
    
    @Test
    void givenNoUsernameWhenGetAuthorizeThenReturnUnauthorized() throws Exception {
        mvc.perform(post("/authenticated"))
        		.andExpect(status().isUnauthorized());
    }
    
    @Test
    @WithMockUser(username = "Mock Username")
    void givenValidUsernameWhenGetAuthorizeThenReturnStatus200() throws Exception {
        mvc.perform(post("/authenticated"))
        		.andExpect(status().isOk());
    }
}
