package com.ICS.ImageClassifier.repositories;

import com.ICS.ImageClassifier.models.entities.ImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, String> {
}
