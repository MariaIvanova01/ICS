package com.ICS.ImageClassifier.services;

import com.ICS.ImageClassifier.models.entities.ImageEntity;
import com.ICS.ImageClassifier.models.entities.TagsEntity;
import com.ICS.ImageClassifier.models.rest.models.Image;
import com.ICS.ImageClassifier.models.rest.models.ImageResponse;
import com.ICS.ImageClassifier.models.rest.models.Tags;
import com.ICS.ImageClassifier.models.service.models.ImageBuilder;
import com.ICS.ImageClassifier.repositories.ImageRepository;
import com.ICS.ImageClassifier.repositories.TagsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    private final ImageClassificationWrapper imageClassificationWrapper;

    private final TagsRepository tagsRepository;

    public ImageService(ImageRepository imageRepository, ImageClassificationWrapper imageClassificationWrapper, TagsRepository tagsRepository) {
        this.imageRepository = imageRepository;
        this.imageClassificationWrapper = imageClassificationWrapper;
        this.tagsRepository = tagsRepository;
    }

    public Optional<ImageResponse> findImageByImageURL(String imageURL) throws Exception {

        Optional<ImageEntity> existingImage = this.imageRepository.findById(imageURL);

        return Optional.of(ImageResponse.builder()
                .status(HttpStatus.OK)
                .image(toImage(
                        existingImage.get().getImageUrl(),
                        existingImage.get().getTagsEntities()))
                .build());

    }
    public Image toImage(String imageURL, List<TagsEntity> tags){

       return Image.builder()
                .imageURL(imageURL)
                .tags(tags.stream().map(tag ->
                        Tags.builder()
                                .tagName(tag.getTagName())
                                .tagAccuracy(tag.getTagAccuracy())
                                .build()
                ).toList())
                .build();
    }

    public ImageResponse addImage(String imageURL, int imageWidth, int imageHeight) throws IOException {
        ImageBuilder imageBuilder = imageClassificationWrapper.classifyImage(imageURL, imageWidth, imageHeight);
        this.imageRepository.save(ServiceToEntityConverter.convertToImageEntity(imageBuilder));
        List<TagsEntity> tagsEntities = ServiceToEntityConverter.convertToTagsEntity(imageBuilder.getTagsBuilderList());
        this.tagsRepository.saveAll(tagsEntities);
        return ImageResponse.builder()
                .status(HttpStatus.OK)
                .image(toImage(imageBuilder.getImageURL(), tagsEntities)).build();
    }

    public ImageResponse returnError(){
        return ImageResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("Image URL is not valid!")
                        .build();

    }

    public ImageResponse getExistingImageError(){
            return ImageResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Image with provided URL does not exist!")
                    .build();

    }

    public List<Image> getAllImages(){
        Iterable<ImageEntity> imageEntities = imageRepository.findAll();
        List<Image> images = new ArrayList<>();
        for (ImageEntity imageEntity : imageEntities) {
            images.add(toImage(
                    imageEntity.getImageUrl(),
                    imageEntity.getTagsEntities())
            );
        }
        return images;
    }
}
