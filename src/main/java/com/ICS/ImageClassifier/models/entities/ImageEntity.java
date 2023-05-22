package com.ICS.ImageClassifier.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "image")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {
    @Id
    @Column(name = "image_url",nullable = false,unique = true)
    private String imageUrl;

    @Column(name="submit_time", nullable=false)
    private LocalDate submitDate;

    @Column(name = "image_width", nullable = false)
    private int imageWidth;

    @Column(name = "image_height", nullable = false)
    private int imageHeight;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "relation_image_tags", joinColumns = {@JoinColumn(name = "image_id")},
    inverseJoinColumns = { @JoinColumn(name = "tags_id")})
    private List<TagsEntity> tagsEntities;

}
