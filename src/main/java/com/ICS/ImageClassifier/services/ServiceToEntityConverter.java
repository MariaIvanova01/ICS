package com.ICS.ImageClassifier.services;

import com.ICS.ImageClassifier.models.entities.ImageEntity;
import com.ICS.ImageClassifier.models.entities.TagsEntity;
import com.ICS.ImageClassifier.models.service.models.ImageBuilder;
import com.ICS.ImageClassifier.models.service.models.TagsBuilder;

import java.util.ArrayList;
import java.util.List;

public class ServiceToEntityConverter {
    public static ImageEntity convertToImageEntity(ImageBuilder imageBuilder) {
        return ImageEntity.builder()
                .imageUrl(imageBuilder.getImageURL())
                .submitDate(imageBuilder.getSubmitDate())
                .imageWidth(imageBuilder.getImageWidth())
                .imageHeight(imageBuilder.getImageHeight())
                .tagsEntities(imageBuilder.getTagsBuilderList().stream().map(tag ->
                        TagsEntity.builder()
                                .tagID(tag.getTagID())
                                .tagName(tag.getTagName())
                                .tagAccuracy(tag.getTagAccuracy())
                                .build()
                ).toList())
                .build();
    }

    public static List<TagsEntity> convertToTagsEntity(List<TagsBuilder> tagsBuilder) {
        List<TagsEntity> tagsEntities = new ArrayList<>();

        for (TagsBuilder tag : tagsBuilder) {
            tagsEntities.add(TagsEntity.builder()
                    .tagID(tag.getTagID())
                    .tagName(tag.getTagName())
                    .tagAccuracy(tag.getTagAccuracy())
                    .build());
        }
        return tagsEntities;
    }
}
