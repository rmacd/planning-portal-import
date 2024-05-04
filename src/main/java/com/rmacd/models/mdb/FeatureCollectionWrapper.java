package com.rmacd.models.mdb;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feature_collections")
public class FeatureCollectionWrapper {

    String id;
    String featureCollection;

    public FeatureCollectionWrapper(String id, String fc) {
        this.id = id;
        this.featureCollection = fc;
    }

    public String getId() {
        return id;
    }

    public FeatureCollectionWrapper setId(String id) {
        this.id = id;
        return this;
    }

    public String getFeatureCollection() {
        return featureCollection;
    }

    public FeatureCollectionWrapper setFeatureCollection(String featureCollection) {
        this.featureCollection = featureCollection;
        return this;
    }
}
