package com.ICS.ImageClassifier.controllers;

import com.ICS.ImageClassifier.exceptions.ApiException;
import com.ICS.ImageClassifier.models.entities.ImageEntity;
import com.ICS.ImageClassifier.models.entities.TagsEntity;
import com.ICS.ImageClassifier.models.rest.models.Image;
import com.ICS.ImageClassifier.models.rest.models.ImageRequest;
import com.ICS.ImageClassifier.models.rest.models.ImageResponse;
import com.ICS.ImageClassifier.repositories.TagsRepository;
import com.ICS.ImageClassifier.services.ImageClassificationWrapper;
import com.ICS.ImageClassifier.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
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
                return this.imageService.addImage(
                        imageRequest.getImageURL(),
                        imageRequest.getImageWidth(),
                        imageRequest.getImageHeight()
                );
            }
        }catch (Exception e){
            return this.imageService.returnError();
        }
    }

    @GetMapping("/getImage/{imageUrl}")
    public ImageResponse getImage(@RequestParam("imageUrl") String imageUrl){
        try {
            Optional<ImageResponse> existingImage = this.imageService.findImageByImageURL(imageUrl);
            if (existingImage.isPresent()){
                return existingImage.get();
            }else {
                return this.imageService.getExistingImageError();

            }
        }catch (Exception e){
            return this.imageService.returnError();
        }
    }

    @GetMapping("/getAllImages")
    public List<Image> getAllImages(){
        return this.imageService.getAllImages();
    }

}
