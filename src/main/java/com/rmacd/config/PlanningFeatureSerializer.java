package com.rmacd.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;

import java.io.IOException;

public class PlanningFeatureSerializer extends JsonSerializer<Geometry> {
    @Override
    public void serialize(Geometry geometry, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObjectField("geometry", new GeometryJSON().read(geometry));
    }
}
