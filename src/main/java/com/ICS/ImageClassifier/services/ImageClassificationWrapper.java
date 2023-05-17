package com.ICS.ImageClassifier.services;

import com.ICS.ImageClassifier.exceptions.ApiException;
import com.ICS.ImageClassifier.models.rest_models.Tags;
import com.clarifai.channel.ClarifaiChannel;
import com.clarifai.credentials.ClarifaiCallCredentials;
import com.clarifai.grpc.api.*;
import com.clarifai.grpc.api.status.StatusCode;

import java.util.ArrayList;
import java.util.List;

public class ImageClassificationWrapper {

    private ApiException apiException;
    //TODO Implement method to access the third-party API passing ImageURL and handling the response (text manipulation)

    public static List<Tags> classifyImage(String imageURL){

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

        List<Tags> tags = new ArrayList<>();
        for (Concept concept : response.getOutputs(0).getData().getConceptsList()) {
           tags.add(Tags
                   .builder()
                   .tagName(concept.getName())
                   .tagAccuracy(concept.getValue())
                   .build());
        }
        return tags;
    }


}
