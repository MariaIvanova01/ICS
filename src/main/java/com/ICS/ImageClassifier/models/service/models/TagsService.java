package com.ICS.ImageClassifier.models.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// TODO: This is annotated and used as a builder, not service. Please rename it the TagsBuilder.

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TagsService {

    private int tagID;

    private String tagName;

    private float tagAccuracy;
}
