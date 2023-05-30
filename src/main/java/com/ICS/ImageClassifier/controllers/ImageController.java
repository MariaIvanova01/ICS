package com.ICS.ImageClassifier.controllers;

import com.ICS.ImageClassifier.exceptions.ApiException;
import com.ICS.ImageClassifier.models.entities.ImageEntity;
import com.ICS.ImageClassifier.models.rest.models.Image;
import com.ICS.ImageClassifier.models.rest.models.ImageRequest;
import com.ICS.ImageClassifier.models.rest.models.Tags;
import com.ICS.ImageClassifier.models.service.models.ImageBuilder;
import com.ICS.ImageClassifier.repositories.ImageRepository;
import com.ICS.ImageClassifier.repositories.TagsRepository;
import com.ICS.ImageClassifier.services.ImageClassificationWrapper;
import com.ICS.ImageClassifier.services.ServiceToEntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class ImageController {

    // TODO: do not expose repositories to controller, instead introduce ImageService and TagService
    private final ImageRepository imageRepository;
    private final TagsRepository tagsRepository;
    private final ImageClassificationWrapper imageClassificationWrapper;
    public ImageController(ImageRepository imageRepository, TagsRepository tagsRepository, ImageClassificationWrapper imageClassificationWrapper) {
        this.imageRepository = imageRepository;
        this.tagsRepository = tagsRepository;
        this.imageClassificationWrapper = imageClassificationWrapper;
    }

    @PostMapping("/processImage")
    public ResponseEntity createImage(@RequestBody ImageRequest imageRequest)  {
        try {
            Optional<ImageEntity> existingImage = this.imageRepository.findById(imageRequest.getImageURL());
            if (existingImage.isPresent()){

                // TODO: better to have ImageResponse model, instead if building it here
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
                ImageBuilder imageBuilder = imageClassificationWrapper.classifyImage(
                        imageRequest.getImageURL(),
                        imageRequest.getImageWidth(),
                        imageRequest.getImageHeight()
                );
                this.imageRepository.save(ServiceToEntityConverter.convertToImageEntity(imageBuilder));
                this.tagsRepository.saveAll(ServiceToEntityConverter.convertToTagsEntity(imageBuilder.getTagsBuilderList()));

                return new ResponseEntity(
                        Image.builder()
                                .imageURL(imageBuilder.getImageURL())
                                .tags(imageBuilder.getTagsBuilderList().stream().map(tag ->
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
    @GetMapping("/getImage")
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
                                .status(HttpStatus.NOT_FOUND)
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