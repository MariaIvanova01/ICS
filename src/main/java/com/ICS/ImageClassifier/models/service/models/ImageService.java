package com.ICS.ImageClassifier.models.service.models;

import com.ICS.ImageClassifier.models.rest.models.Tags;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ImageService {

    private String imageURL;

    private List<Tags> tags;

}
