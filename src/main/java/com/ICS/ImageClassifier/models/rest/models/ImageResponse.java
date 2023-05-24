package com.ICS.ImageClassifier.models.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
public class ImageResponse {

    private HttpStatus status;
    private Image image;
    private String message;

}
