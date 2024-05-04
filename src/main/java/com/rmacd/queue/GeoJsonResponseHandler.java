package com.rmacd.queue;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.google.gson.Gson;
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
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.UUID;

import static org.geotools.referencing.crs.DefaultGeographicCRS.WGS84;

public class GeoJsonResponseHandler implements ResponseHandler<GeometryJSON> {

    private static final Logger logger = LoggerFactory.getLogger(GeoJsonResponseHandler.class);

    final FeatureCollectionRepo featureCollectionRepo;
    final ElasticsearchClient esClient;

    // MathTransform to transform from source CRS to target CRS
    static MathTransform transform;

    static {
        try {
            CoordinateReferenceSystem sourceCRSObj = CRS.decode("EPSG:27700");
            transform = CRS.findMathTransform(sourceCRSObj, WGS84);
        } catch (FactoryException e) {
            throw new RuntimeException(e);
        }
    }

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
                Geometry reprojected = JTS.transform(geometry, transform);

                Gson gson = new Gson();
                Object esObj = gson.fromJson(new GeometryJSON(6).toString(reprojected), Object.class);

                IndexResponse r = esClient.index(i -> i
                        .index("planning-features")
                        .id(UUID.randomUUID().toString())
                        .withJson(new StringReader(new GeometryJSON(6).toString(reprojected)))
                );

                logger.info("Wrote document {}", r.toString());
            }
        } catch (TransformException e) {
            throw new RuntimeException(e);
        } finally {
            iterator.close();
        }
        return null;
    }
}
