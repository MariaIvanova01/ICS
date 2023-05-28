package com.ICS.ImageClassifier.services;

import com.ICS.ImageClassifier.models.service.models.ImageBuilder;
import com.ICS.ImageClassifier.models.service.models.TagsBuilder;
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

    @Value("${classifier.api}")
    private String api;

    @Value("${MODEL_ID}")
    private String model;

    public ImageBuilder classifyImage(String imageURL, int imageWidth, int imageHeight) throws IOException {
        ImageBuilder imageBuilder = new ImageBuilder(imageURL, imageWidth, imageHeight, invokeClarifai(imageURL));
        return imageBuilder;
    }

    private static List<TagsService> invokeClarifai(String imageURL){

        V2Grpc.V2BlockingStub stub = V2Grpc.newBlockingStub(ClarifaiChannel.INSTANCE.getGrpcChannel())
                .withCallCredentials(new ClarifaiCallCredentials(api));


        MultiOutputResponse response = stub.postModelOutputs(
                PostModelOutputsRequest.newBuilder()
                        .setModelId(model)
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


        List<TagsBuilder> tags = new ArrayList<>();

        for (Concept concept : response.getOutputs(0).getData().getConceptsList()) {
            if (concept.getValue() >= 0.93) {
                tags.add(TagsBuilder
                        .builder()
                        .tagName(concept.getName())
                        .tagAccuracy(concept.getValue())
                        .build());
            }
        }
        return tags;
    }
}
