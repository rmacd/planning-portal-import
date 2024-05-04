package com.rmacd.repos.es;

import com.rmacd.models.es.PlanningFeature;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PlanningFeatureRepo extends ElasticsearchRepository<PlanningFeature, String> {
}
