package com.ICS.ImageClassifier.controllers;

import com.ICS.ImageClassifier.models.entities.ImageEntity;
import com.ICS.ImageClassifier.models.entities.TagsEntity;
import com.ICS.ImageClassifier.models.rest.models.ImageRequest;
import com.ICS.ImageClassifier.repositories.ImageRepository;
import com.ICS.ImageClassifier.repositories.TagsRepository;
import com.ICS.ImageClassifier.services.ImageClassificationWrapper;
import com.ICS.ImageClassifier.services.ImageService;
import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ImageController.class)
public class ImageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService imageService;
    @MockBean
    private EntityManager entityManager;
    @MockBean
    private EntityManagerFactory entityManagerFactory;
    @MockBean
    private ImageRepository imageRepository;

    @MockBean
    private TagsRepository tagsRepository;

    @MockBean
    private ImageClassificationWrapper imageClassificationWrapper;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(imageRepository);
        MockitoAnnotations.openMocks(imageService);
    }

    /*@Test
    public void testCreateImageOnPostPositive() throws Exception {
        String url = "https://travelsteps.net/uploads/more-prez-septemvri.jpg";
        ImageRequest imageRequest = new ImageRequest(url, 1080, 1960);


        Gson gson = new Gson();
        mockMvc.perform(post("/rest/getImageURL")
                .contentType("application/json")
                .content(gson.toJson(imageRequest)))
                .andExpect(status().isOk());

        verify(imageRepository, times(1)).save(any());
        verify(tagsRepository, times(14)).save(any());
    }

    @Test
    public void testCreateImageOnPostNegative() throws Exception {
        String url = "https://travelsteps.net/";
        ImageRequest imageRequest = new ImageRequest(url, 1080, 1960);

        Gson gson = new Gson();

        mockMvc.perform(post("/rest/getImageURL")
                        .contentType("application/json")
                        .content(gson.toJson(imageRequest)))
                .andExpect(status().is5xxServerError());

    }*/

    @Test
    public void testGetAllImagesPositive() throws Exception {

        when(imageRepository.findAll()).thenReturn(Collections.singletonList(
                ImageEntity.builder()
                        .imageUrl("https://travelsteps.net/uploads/more-prez-septemvri.jpg")
                        .tagsEntities(Collections.singletonList(TagsEntity.builder()
                                .tagID(1)
                                .tagName("test-tag")
                                .tagAccuracy(0.95f).build()))
                        .build()
        ));

        mockMvc.perform(get("/getAllImages")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testNotSavingExistingImagePositive() throws Exception {
        String url = "https://travelsteps.net/uploads/more-prez-septemvri.jpg";

        doReturn(Optional.of(ImageEntity.builder()
                .imageUrl(url)
                .tagsEntities(Collections.singletonList(TagsEntity.builder()
                        .tagID(1)
                        .tagName("test-tag")
                        .tagAccuracy(0.95f).build()))
                .build()))
                .when(imageRepository).findById(url);

        Gson gson = new Gson();
        ImageRequest imageRequest = new ImageRequest(url, 1080, 1960);
        mockMvc.perform(post("/rest/getImageURL")
                        .contentType("application/json")
                        .content(gson.toJson(imageRequest)))
                .andExpect(status().isOk());
        verify(imageRepository, never()).save(any());
        verify(tagsRepository, never()).save(any());
    }

/*    @Test
    public void testGetExistingImagePositive() throws Exception {
        String url = "https://travelsteps.net/uploads/more-prez-septemvri.jpg";

        doReturn(Optional.of(ImageEntity.builder()
                .imageUrl(url)
                .tagsEntities(Collections.singletonList(TagsEntity.builder()
                        .tagID(1)
                        .tagName("test-tag")
                        .tagAccuracy(0.95f).build()))
                .build()))
                .when(imageRepository).findById(url);

        mockMvc.perform(get("/getImage/{imageUrl}")
                        .param("imageUrl", url))
                .andDo(print())
                .andExpect(status().isOk());
    }*/

    @Test
    public void testGetExistingImageNegative() throws Exception {
        String url = "https://travelsteps.net/uploads/more-prez-septemvri.jpg";

        mockMvc.perform(get("/getImage/{imageUrl}", url)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}