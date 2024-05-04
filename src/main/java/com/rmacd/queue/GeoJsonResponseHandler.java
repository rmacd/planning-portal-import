package com.rmacd.queue;

import com.rmacd.models.es.PlanningFeature;
import com.rmacd.models.mdb.FeatureCollectionWrapper;
import com.rmacd.repos.es.PlanningFeatureRepo;
import com.rmacd.repos.mdb.FeatureCollectionRepo;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geojson.geom.GeometryJSON;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeoJsonResponseHandler implements ResponseHandler<GeometryJSON> {

    private static final Logger logger = LoggerFactory.getLogger(GeoJsonResponseHandler.class);

    final FeatureCollectionRepo featureCollectionRepo;
    final PlanningFeatureRepo planningFeatureRepo;

    public GeoJsonResponseHandler(
            FeatureCollectionRepo featureCollectionRepo,
            PlanningFeatureRepo planningFeatureRepo
    ) {
        this.featureCollectionRepo = featureCollectionRepo;
        this.planningFeatureRepo = planningFeatureRepo;
    }

    @Override
    public GeometryJSON handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        String thisResponse = EntityUtils.toString(response.getEntity());
        FeatureCollection fc = new FeatureJSON().readFeatureCollection(thisResponse);
        featureCollectionRepo.save(new FeatureCollectionWrapper("_test", thisResponse));
        FeatureIterator<SimpleFeature> iterator = fc.features();

//        List<Geometry> geometries = new ArrayList<>();

        try {
            while (iterator.hasNext()) {
                Feature feature = iterator.next();
                feature.getIdentifier().getID();
                // add geo to list of geos
                Geometry geometry = (Geometry) ((SimpleFeatureImpl)feature).getDefaultGeometry();
                planningFeatureRepo.save(new PlanningFeature(geometry));
//                geometries.add((Geometry) ((SimpleFeatureImpl)feature).getDefaultGeometry());
            }
        } finally {
            iterator.close();
        }

//        GeometryCollection geometryCollection = new GeometryCollection(geometries.toArray(new Geometry[0]), new GeometryFactory());
//        PlanningFeature pf = new PlanningFeature(geometries);
//        planningFeatureRepo.save(pf);
        return null;
    }
}
