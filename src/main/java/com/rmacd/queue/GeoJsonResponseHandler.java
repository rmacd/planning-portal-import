package com.rmacd.queue;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import com.rmacd.models.mdb.FeatureCollectionWrapper;
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
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;

public class GeoJsonResponseHandler implements ResponseHandler<GeometryJSON> {

    private static final Logger logger = LoggerFactory.getLogger(GeoJsonResponseHandler.class);

    final FeatureCollectionRepo featureCollectionRepo;
    final ElasticsearchClient esClient;

    public GeoJsonResponseHandler(
            FeatureCollectionRepo featureCollectionRepo, ElasticsearchClient esClient
    ) {
        this.featureCollectionRepo = featureCollectionRepo;
        this.esClient = esClient;
    }

    @Override
    public GeometryJSON handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        String thisResponse = EntityUtils.toString(response.getEntity());
        FeatureCollection fc = new FeatureJSON().readFeatureCollection(thisResponse);
        featureCollectionRepo.save(new FeatureCollectionWrapper(UUID.randomUUID().toString(), thisResponse));
        FeatureIterator<SimpleFeature> iterator = fc.features();


        try {
            while (iterator.hasNext()) {
                Feature feature = iterator.next();
                feature.getIdentifier().getID();
                // add geo to list of geos
                Geometry geometry = (Geometry) ((SimpleFeatureImpl)feature).getDefaultGeometry();

//                esClient.index(i -> i
//                        .index("planning-features")
//                        .id(UUID.randomUUID().toString())
//                        .document(geometry)
//                );

            }
        } finally {
            iterator.close();
        }
        return null;
    }
}
