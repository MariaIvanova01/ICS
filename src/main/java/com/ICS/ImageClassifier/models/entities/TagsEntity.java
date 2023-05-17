package com.ICS.ImageClassifier.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "image_tags")
public class TagsEntity {

    @Id
    @GeneratedValue
    @Column(name = "tag_id", nullable = false,unique = true)
    private int tagID;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    @Column(name = "tags_accuracy", nullable = false)
    private float tagAccuracy;

}
