package com.example.Fipe_RestApi.repository;

import com.example.Fipe_RestApi.model.FipeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FipeRepository extends MongoRepository<FipeEntity, String> {
}