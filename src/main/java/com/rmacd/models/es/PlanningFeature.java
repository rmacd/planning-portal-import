package com.rmacd.models.es;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rmacd.config.PlanningFeatureSerializer;
import org.locationtech.jts.geom.Geometry;

public class PlanningFeature {

    String id;

    @JsonSerialize(using = PlanningFeatureSerializer.class)
    Geometry geometry;

    public PlanningFeature(Geometry geometry) {
        this.geometry = geometry;
    }
}
