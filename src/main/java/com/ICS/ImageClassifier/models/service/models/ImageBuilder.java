package com.ICS.ImageClassifier.models.service.models;

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
public class ImageBuilder {

    private String imageURL;

    private LocalDate submitDate;

    private int imageWidth;

    private int imageHeight;

    private List<TagsBuilder> tagsBuilderList;

    public ImageBuilder(String imageUrl, int imageWidth, int imageHeight, List<TagsBuilder> tagsBuilderList) {
        this.imageURL = imageUrl;
        setSubmitDate();
        this.tagsBuilderList = tagsBuilderList;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public void setSubmitDate() {
        this.submitDate = LocalDate.now();
    }

}
