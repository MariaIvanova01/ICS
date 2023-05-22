package com.ICS.ImageClassifier.controllers;

import com.ICS.ImageClassifier.exceptions.ApiException;
import com.ICS.ImageClassifier.models.entities.ImageEntity;
import com.ICS.ImageClassifier.models.entities.TagsEntity;
import com.ICS.ImageClassifier.models.rest.models.Image;
import com.ICS.ImageClassifier.models.rest.models.ImageRequest;
import com.ICS.ImageClassifier.models.rest.models.Tags;
import com.ICS.ImageClassifier.models.service.models.ImageService;
import com.ICS.ImageClassifier.models.service.models.TagsService;
import com.ICS.ImageClassifier.repositories.ImageRepository;
import com.ICS.ImageClassifier.repositories.TagsRepository;
import com.ICS.ImageClassifier.services.ImageClassificationWrapper;
import com.ICS.ImageClassifier.services.ServiceToEntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
public class ImageController {

    private final ImageRepository imageRepository;
    private final TagsRepository tagsRepository;
    private String imageUrl;

    public ImageController(ImageRepository imageRepository, TagsRepository tagsRepository) {
        this.imageRepository = imageRepository;
        this.tagsRepository = tagsRepository;
    }

    @PostMapping("/rest/getImageURL")
    public ResponseEntity createImage(@RequestBody ImageRequest imageRequest)  {
        try {
            Optional<ImageEntity> existingImage = this.imageRepository.findById(imageRequest.getImageURL());
            if (existingImage.isPresent()){

                return new ResponseEntity(
                        Image.builder()
                        .imageURL(existingImage.get().getImageUrl())
                        .tags(existingImage.get().getTagsEntities().stream().map(tag ->
                                Tags.builder()
                                        .tagName(tag.getTagName())
                                        .tagAccuracy(tag.getTagAccuracy())
                                        .build()
                        ).toList())
                        .build(),
                        HttpStatus.OK);
            }else {
                ImageService imageService = ImageClassificationWrapper.classifyImage(
                        imageRequest.getImageURL(),
                        imageRequest.getImageWidth(),
                        imageRequest.getImageHeight()
                );

                this.imageRepository.save(ServiceToEntityConverter.convertToImageEntity(imageService));
                for (TagsService tag : imageService.getTagsServiceList()) {
                    this.tagsRepository.save(ServiceToEntityConverter.convertToTagsEntity(tag));
                }

                return new ResponseEntity(
                        Image.builder()
                                .imageURL(imageService.getImageURL())
                                .tags(imageService.getTagsServiceList().stream().map(tag ->
                                        Tags.builder()
                                                .tagName(tag.getTagName())
                                                .tagAccuracy(tag.getTagAccuracy())
                                                .build()
                                ).toList())
                                .build(),
                        HttpStatus.OK
                );
            }
        }catch (Exception e){
            return new ResponseEntity<>(
                    ApiException.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .timestamp(LocalDateTime.now())
                        .message("Image URL is not valid!")
                        .remedy_message("Check weather the URL is in the correct format.")
                        .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getImage/{imageUrl}")
    public ResponseEntity getImage(@RequestParam("imageUrl") String imageUrl){
        try {
            Optional<ImageEntity> existingImage = this.imageRepository.findById(imageUrl);
            if (existingImage.isPresent()){
                return new ResponseEntity(
                        Image.builder()
                                .imageURL(existingImage.get().getImageUrl())
                                .tags(existingImage.get().getTagsEntities().stream().map(tag ->
                                        Tags.builder()
                                                .tagName(tag.getTagName())
                                                .tagAccuracy(tag.getTagAccuracy())
                                                .build()
                                ).toList())
                                .build(),
                        HttpStatus.OK);
            }else {
                return new ResponseEntity<>(
                        ApiException.builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .timestamp(LocalDateTime.now())
                                .message("Image with provided URL does not exist!")
                                .remedy_message("For image classification please submit the image URL!")
                                .build(),
                        HttpStatus.NOT_FOUND
                );
            }
        }catch (Exception e){
            return new ResponseEntity<>(
                    ApiException.builder()
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .timestamp(LocalDateTime.now())
                            .message("Image URL is not valid!")
                            .remedy_message("Check weather the URL is in the correct format.")
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/getAllImages")
    public List<Image> getAllImages(){
        Iterable<ImageEntity> imageEntities = imageRepository.findAll();
        List<Image> images = new ArrayList<>();
        for (ImageEntity imageEntity : imageEntities) {
            images.add(Image.builder()
                    .imageURL(imageEntity.getImageUrl())
                    .tags(imageEntity.getTagsEntities().stream().map(tagsEntity ->
                                    Tags.builder()
                                            .tagName(tagsEntity.getTagName())
                                            .tagAccuracy(tagsEntity.getTagAccuracy())
                                            .build()
                            ).toList()
                    ).build());
        }

        return images;
    }

}
