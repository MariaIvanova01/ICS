package com.ICS.ImageClassifier.models.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ImageDto {

    private String imageUrl;

    private Date submitDate;

    private double imageSize;

}
