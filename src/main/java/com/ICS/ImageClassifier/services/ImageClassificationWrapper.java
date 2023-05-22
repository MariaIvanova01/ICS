package com.ICS.ImageClassifier.services;

import com.ICS.ImageClassifier.exceptions.ApiException;
import com.ICS.ImageClassifier.models.service.models.ImageService;
import com.ICS.ImageClassifier.models.service.models.TagsService;
import com.clarifai.channel.ClarifaiChannel;
import com.clarifai.credentials.ClarifaiCallCredentials;
import com.clarifai.grpc.api.*;
import com.clarifai.grpc.api.status.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.util.*;

@Component
public class ImageClassificationWrapper {

    @Value("${CLASSIFIER_API}")
    private static final String CLASSIFIER_API = null;

    @Value("${MODEL_ID}")
    private static final String MODEL_ID = null;

    private ApiException apiException;
    public static ImageService classifyImage(String imageURL, int imageWidth, int imageHeight) throws IOException {
        ImageService imageService = new ImageService(imageURL, imageWidth, imageHeight, invokeClarifai(imageURL));
        return imageService;
    }

    private static List<TagsService> invokeClarifai(String imageURL){

        V2Grpc.V2BlockingStub stub = V2Grpc.newBlockingStub(ClarifaiChannel.INSTANCE.getGrpcChannel())
                .withCallCredentials(new ClarifaiCallCredentials(CLASSIFIER_API));


        MultiOutputResponse response = stub.postModelOutputs(
                PostModelOutputsRequest.newBuilder()
                        .setModelId(MODEL_ID)
                        .addInputs(
                                Input.newBuilder().setData(
                                        Data.newBuilder().setImage(
                                                Image.newBuilder().setUrl(imageURL)
                                        )
                                )
                        )
                        .build()
        );

        if (response.getStatus().getCode() != StatusCode.SUCCESS) {
            throw new IllegalArgumentException("Request failed, status: " + response.getStatus());
        }


        List<TagsService> tags = new ArrayList<>();

        for (Concept concept : response.getOutputs(0).getData().getConceptsList()) {
            if (concept.getValue() >= 0.93) {
                tags.add(TagsService
                        .builder()
                        .tagName(concept.getName())
                        .tagAccuracy(concept.getValue())
                        .build());
            }
        }
        return tags;
    }
}
