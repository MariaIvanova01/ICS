package com.ICS.ImageClassifier.controllers;

import com.ICS.ImageClassifier.exceptions.ApiException;
import com.ICS.ImageClassifier.models.rest_models.Image;
import com.ICS.ImageClassifier.services.ImageClassificationWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ImageController {
    //TODO Implement Post Method for v1/image (my API that receives the image URL)

    @PostMapping("/rest/getImageURL")
    public ResponseEntity createImage(@RequestBody String imageURL)  {
        //TODO Save Image to Database (additionally in user session)
        try {
            return  new ResponseEntity(
                    Image.builder()
                        .imageURL(imageURL)
                        .tags(ImageClassificationWrapper.classifyImage(imageURL))
                        .build(),
                    HttpStatus.OK
            );
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


    //TODO as parameter to get imgURL, call method classifyImage  -> for sprint two
    // call the method from image repo to add it in the database -> for sprint two
    // to construct the object image and return it as response


    //TODO Implement GET Method to return images (additionally to return images for specific user) -> for sprint two

    //TODO Implement Delete Method to remove image (additionally for specific user) ? -> for sprint two
}
