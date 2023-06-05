package com.ICS.ImageClassifier.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tags")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
