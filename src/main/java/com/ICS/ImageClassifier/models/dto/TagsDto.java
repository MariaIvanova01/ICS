package com.ICS.ImageClassifier.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TagsDto {

    private int tagID;

    private String tagName;

    private float tagAccuracy;

}
