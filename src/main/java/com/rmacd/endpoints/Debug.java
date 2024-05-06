package com.rmacd.endpoints;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.rmacd.models.PlanningDetails;
import com.rmacd.models.es.PlanningFeature;
import com.rmacd.services.MetadataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Debug {

    private static final Logger logger = LoggerFactory.getLogger(Debug.class);
    final ElasticsearchClient esClient;
    final MetadataService metadataService;

    public Debug(ElasticsearchClient esClient, MetadataService metadataService) {
        this.esClient = esClient;
        this.metadataService = metadataService;
    }

    @GetMapping("/debug/update-stls")
    public void doUpdateSTLs() {
        Query fulstlQuery = new MatchQuery.Builder()
                .field("derived_type")
                .query("FULSTL")
                .build()._toQuery();

        try {
            SearchResponse<PlanningFeature> response = esClient.search(new SearchRequest.Builder()
                            .index("planning-features")
                            .query(fulstlQuery)
                            .build(), PlanningFeature.class
            );
            for (Hit<PlanningFeature> hit : response.hits().hits()) {
                if (null != hit.source()) {
                    PlanningFeature feature = hit.source();
                    PlanningDetails details = metadataService.getPlanningDetails(feature.getAuthority(), feature.getKeyval());
                    logger.info("Got details: {}", details);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
