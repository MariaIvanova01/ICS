package com.ICS.ImageClassifier.services;

import com.ICS.ImageClassifier.models.entities.ImageEntity;
import com.ICS.ImageClassifier.models.entities.TagsEntity;
import com.ICS.ImageClassifier.models.service.models.ImageService;
import com.ICS.ImageClassifier.models.service.models.TagsService;

public class ServiceToEntityConverter {
    public static ImageEntity convertToImageEntity(ImageService imageService) {
        return ImageEntity.builder()
                .imageUrl(imageService.getImageURL())
                .submitDate(imageService.getSubmitDate())
                .imageWidth(imageService.getImageWidth())
                .imageHeight(imageService.getImageHeight())
                .tagsEntities(imageService.getTagsServiceList().stream().map(tag ->
                        TagsEntity.builder()
                                .tagID(tag.getTagID())
                                .tagName(tag.getTagName())
                                .tagAccuracy(tag.getTagAccuracy())
                                .build()
                ).toList())
                .build();
    }

    public static TagsEntity convertToTagsEntity(TagsService tagsService) {
        return TagsEntity.builder()
                .tagID(tagsService.getTagID())
                .tagName(tagsService.getTagName())
                .tagAccuracy(tagsService.getTagAccuracy())
                .build();
    }
}
