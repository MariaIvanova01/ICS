package com.ICS.ImageClassifier.models.entities;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "image_ics")
public class ImageEntity {
    @Id
    @Column(name = "image_url",nullable = false,unique = true)
    private String imageUrl;

    @Column(name="submit_time", nullable=false)
    private Date submitDate;

    @Column(name = "image_size", nullable = false)
    private double imageSize;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "relation_image_tags", joinColumns = {@JoinColumn(name = "image_id")},
    inverseJoinColumns = { @JoinColumn(name = "tags_id")})
    private List<TagsEntity> tagsEntities;
}
