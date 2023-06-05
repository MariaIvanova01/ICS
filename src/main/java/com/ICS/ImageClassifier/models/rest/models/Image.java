package com.ICS.ImageClassifier.models.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Image {

    private String imageURL;

    private List<Tags> tags;

    private LocalDate date;

}
