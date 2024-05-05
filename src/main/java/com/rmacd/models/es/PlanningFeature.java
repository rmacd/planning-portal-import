package com.rmacd.models.es;

import org.locationtech.jts.geom.Geometry;

public class PlanningFeature {

    Geometry geometry;

    public PlanningFeature(Geometry geometry) {
        this.geometry = geometry;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public PlanningFeature setGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }
}
