package com.ICS.ImageClassifier.models.rest.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageRequest {
    // TODO: question - As a client of the API, how can I get the dimensions of the image?
    // TODO: check the requirements
    private String imageURL;

    private int imageWidth;

    private int imageHeight;
}
