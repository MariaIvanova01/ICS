package com.ICS.ImageClassifier.services;

import com.ICS.ImageClassifier.exceptions.ApiException;
import com.ICS.ImageClassifier.models.service.models.ImageService;
import com.ICS.ImageClassifier.models.service.models.TagsService;
import com.clarifai.channel.ClarifaiChannel;
import com.clarifai.credentials.ClarifaiCallCredentials;
import com.clarifai.grpc.api.*;
import com.clarifai.grpc.api.status.StatusCode;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class ImageClassificationWrapper {

    private ApiException apiException;
    //TODO Implement method to access the third-party API passing ImageURL and handling the response (text manipulation)

    public static ImageService classifyImage(String imageURL, int imageWidth, int imageHeight) throws IOException {
        ImageService imageService = new ImageService(imageURL, imageWidth, imageHeight, invokeClarifai(imageURL));
        return imageService;
    }

    private static List<TagsService> invokeClarifai(String imageURL){
        // TODO: remove any hardcoded values and extract them as ENV vars (application.properties)
        V2Grpc.V2BlockingStub stub = V2Grpc.newBlockingStub(ClarifaiChannel.INSTANCE.getGrpcChannel())
                .withCallCredentials(new ClarifaiCallCredentials("042439d583204f26901781c20cbf2194"));


        MultiOutputResponse response = stub.postModelOutputs(
                PostModelOutputsRequest.newBuilder()
                        .setModelId("aaa03c23b3724a16a56b629203edc62c")
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
