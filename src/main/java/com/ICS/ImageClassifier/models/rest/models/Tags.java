package com.ICS.ImageClassifier.models.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Tags {
    private String tagName;

    private float tagAccuracy;

}
