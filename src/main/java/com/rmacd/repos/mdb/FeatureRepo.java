package com.rmacd.repos.mdb;

import org.opengis.feature.Feature;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeatureRepo extends MongoRepository<Feature, String> {
}
