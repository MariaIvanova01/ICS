package com.ICS.ImageClassifier.controllers;

import com.ICS.ImageClassifier.models.rest.models.Image;
import com.ICS.ImageClassifier.models.rest.models.ImageRequest;
import com.ICS.ImageClassifier.models.rest.models.ImageResponse;
import com.ICS.ImageClassifier.repositories.TagsRepository;
import com.ICS.ImageClassifier.services.ImageClassificationWrapper;
import com.ICS.ImageClassifier.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/processImage")
    public ImageResponse createImage(@RequestBody ImageRequest imageRequest)  {
        try {
            Optional<ImageResponse> imageResponse = this.imageService.findImageByImageURL(imageRequest.getImageURL());
            if (imageResponse.isPresent()){
                return imageResponse.get();
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
