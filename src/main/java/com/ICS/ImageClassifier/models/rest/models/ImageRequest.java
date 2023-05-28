package com.ICS.ImageClassifier.models.rest.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageRequest {
    private String imageURL;

    private int imageWidth;

    private int imageHeight;
}
