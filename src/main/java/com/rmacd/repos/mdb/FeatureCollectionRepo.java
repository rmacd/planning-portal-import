package com.rmacd.repos.mdb;

import com.rmacd.models.mdb.FeatureCollectionWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeatureCollectionRepo extends MongoRepository<FeatureCollectionWrapper, String> {
}
