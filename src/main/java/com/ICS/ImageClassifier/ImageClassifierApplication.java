package com.ICS.ImageClassifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ICS.ImageClassifier.repositories")
@EntityScan(basePackages = "com.ICS.ImageClassifier.models.entities")
@EnableConfigurationProperties
public class ImageClassifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageClassifierApplication.class, args);
	}
}
