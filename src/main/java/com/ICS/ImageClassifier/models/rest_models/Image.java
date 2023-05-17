package com.ICS.ImageClassifier.models.rest_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Image {

    private String imageURL;

    private List<Tags> tags;

}
