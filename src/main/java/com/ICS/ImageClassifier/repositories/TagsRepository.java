package com.ICS.ImageClassifier.repositories;

import com.ICS.ImageClassifier.models.entities.TagsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends CrudRepository<TagsEntity, Integer> {
    Iterable<TagsEntity> getAllByTagID(int tagID);
}
