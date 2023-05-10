package com.ICS.ImageClassifier.controllers;

import com.ICS.ImageClassifier.models.Image;
import com.ICS.ImageClassifier.services.ImageClassificationWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ImageController.class)
public class ImageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageClassificationWrapper imageClassificationWrapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void createImageOnPostPositive() throws Exception {
        String url = "https://travelsteps.net/uploads/more-prez-septemvri.jpg";

        mockMvc.perform(post("/rest/getImageURL")
                .contentType("application/json")
                .content(url))
                .andExpect(status().isOk());
    }



}
