package com.rmacd.services;

import com.rmacd.factories.PlanningDetailsFactory;
import com.rmacd.models.AuthorityEnum;
import com.rmacd.models.PlanningDetails;
import com.rmacd.models.mdb.ResponseCache;
import com.rmacd.repos.mdb.ResponseCacheRepo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class MetadataServiceImpl implements MetadataService {

    private static final Logger logger = LoggerFactory.getLogger(MetadataServiceImpl.class);
    final ResponseCacheRepo responseCacheRepo;

    public MetadataServiceImpl(ResponseCacheRepo responseCacheRepo) {
        this.responseCacheRepo = responseCacheRepo;
    }

    @Override
    public PlanningDetails getPlanningDetails(AuthorityEnum authority, String ref) {
        ResponseCache response = responseCacheRepo
                .findById(ResponseCache.generateDetailsId(authority, ref))
                .orElseGet(() -> getDetailsHTML(authority, ref));
        PlanningDetails details = PlanningDetailsFactory.create(response.getDocument());
        return details;
    }

    private ResponseCache getDetailsHTML(AuthorityEnum authority, String ref) {
        logger.info("Requesting details via HTTP request for {}", ref);
        try {
            URI uri = new URIBuilder(authority.getDetailUrlBase()).addParameter("activeTab", "printPreview").addParameter("keyVal", ref).build();
            HttpGet req = new HttpGet(uri);
            try (final CloseableHttpClient httpClient = HttpClients.createDefault(); final CloseableHttpResponse response = httpClient.execute(req)) {
                final String result = EntityUtils.toString(response.getEntity());
                return responseCacheRepo.save(new ResponseCache(ResponseCache.generateDetailsId(authority, ref), result));
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
