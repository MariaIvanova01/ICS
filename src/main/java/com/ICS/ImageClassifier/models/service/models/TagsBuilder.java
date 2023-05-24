package com.ICS.ImageClassifier.models.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TagsBuilder {

    private int tagID;

    private String tagName;

    private float tagAccuracy;
}
