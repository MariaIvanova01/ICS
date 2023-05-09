package com.ICS.ImageClassifier;

import com.clarifai.grpc.api.status.StatusCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.clarifai.grpc.api.*;
import com.clarifai.channel.ClarifaiChannel;
import com.clarifai.credentials.ClarifaiCallCredentials;

@SpringBootApplication
public class ImageClassifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageClassifierApplication.class, args);

	}

}
