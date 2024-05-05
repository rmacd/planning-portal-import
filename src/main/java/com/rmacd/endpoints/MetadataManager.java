package com.rmacd.endpoints;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class MetadataManager {

    final ResponseCacheRepo responseCacheRepo;

    public MetadataManager(ResponseCacheRepo responseCacheRepo) {
        this.responseCacheRepo = responseCacheRepo;
    }

    @GetMapping("/details/{authority}/{ref}")
    public PlanningDetails getDetails(@PathVariable AuthorityEnum authority, @PathVariable String ref) {
        ResponseCache response = responseCacheRepo.findById(ResponseCache.generateDetailsId(authority, ref)).orElse(getDetailsHTML(authority, ref));

        PlanningDetails details = PlanningDetailsFactory.create(response.getDocument());
        return null;
    }

    private ResponseCache getDetailsHTML(AuthorityEnum authority, String ref) {
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
