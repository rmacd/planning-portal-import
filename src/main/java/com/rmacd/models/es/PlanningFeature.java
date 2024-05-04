package com.rmacd.models.es;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rmacd.config.PlanningFeatureSerializer;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoShapeField;

import java.util.List;
import java.util.UUID;

@Document(indexName = "planning-features", alwaysWriteMapping = true)
public class PlanningFeature {

    @Field(type = FieldType.Keyword)
    String id;
//

    public String getId() {
        return id;
    }

    public PlanningFeature setId(String id) {
        this.id = id;
        return this;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public PlanningFeature setGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }
//    @GeoShapeField
//    List<Geometry> geometries;

    @GeoShapeField
    @JsonSerialize(using = PlanningFeatureSerializer.class)
    Geometry geometry;

    public PlanningFeature(List<Geometry> geometries) {
        this.id = UUID.randomUUID().toString();
//        this.geometries = geometries;
    }

    public PlanningFeature(Geometry geometry) {
        this.geometry = geometry;
    }
}
