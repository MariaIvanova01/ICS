package com.ICS.ImageClassifier.controllers;

import com.ICS.ImageClassifier.models.Image;
import com.ICS.ImageClassifier.services.ImageClassificationWrapper;
import org.springframework.web.bind.annotation.*;

@RestController
public class ImageController {
    //TODO Implement Post Method for v1/image (my API that receives the image URL for specific user)

    @PostMapping("/rest/getImageURL")
    public Image createImage(@RequestBody String imageURL) throws Exception {
        //TODO Save Image to Database (additionally in user session)
        try {
            return  Image.builder()
                    .imageURL(imageURL)
                    .tags(ImageClassificationWrapper.classifyImage(imageURL))
                    .build();
        }catch (Exception e){
            throw new Exception("Image classification failed due to third party service being unreachable");
        }


    }


    //TODO as parameter to get imgURL, call method classifyImage  -> for sprint two
    // call the method from image repo to add it in the database -> for sprint two
    // to construct the object image and return it as response


    //TODO Implement GET Method to return images (additionally to return images for specific user) -> for sprint two

    //TODO Implement Delete Method to remove image (additionally for specific user) ? -> for sprint two
}
