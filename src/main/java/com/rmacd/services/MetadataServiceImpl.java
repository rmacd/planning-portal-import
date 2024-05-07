package com.rmacd.services;

import com.rmacd.factories.DocumentDetailsFactory;
import com.rmacd.factories.PlanningDetailsFactory;
import com.rmacd.models.AuthorityEnum;
import com.rmacd.models.DocumentDetails;
import com.rmacd.models.PlanningDetails;
import com.rmacd.models.mdb.ResourceTypeEnum;
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
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
                .findById(ResourceTypeEnum.DETAILS.generateId(authority, ref))
                .orElseGet(() -> getDetailsHTML(authority, ref));
        return PlanningDetailsFactory.create(response);
    }

    @Override
    public List<PlanningDetails> getCachedPlanningDetails() {
        return responseCacheRepo.findByType(ResourceTypeEnum.DETAILS)
                .stream().map(PlanningDetailsFactory::create).toList();
    }

    @Override
    public List<DocumentDetails> getDocumentsDetails(AuthorityEnum authority, String ref) {
        ResponseCache response = responseCacheRepo
                .findById(ResourceTypeEnum.DOCUMENTS.generateId(authority, ref))
                .orElseGet(() -> getDocumentsHTML(authority, ref));
        logger.debug("got response {}", response);
        return DocumentDetailsFactory.create(response);
    }

    @Override
    public List<DocumentDetails> getCachedDocumentDetails() {
        return responseCacheRepo.findByType(ResourceTypeEnum.DOCUMENTS)
                .stream().map(DocumentDetailsFactory::create)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private ResponseCache getDetailsHTML(AuthorityEnum authority, String ref) {
        return getHTML(ResourceTypeEnum.DETAILS, authority, ref);
    }

    private ResponseCache getDocumentsHTML(AuthorityEnum authority, String ref) {
        return getHTML(ResourceTypeEnum.DOCUMENTS, authority, ref);
    }

    private ResponseCache getHTML(ResourceTypeEnum docType, AuthorityEnum authority, String ref) {
        logger.info("Requesting details via HTTP request for {}", ref);
        try {
            URI uri = new URIBuilder(authority.getDetailUrlBase()).addParameter("activeTab", docType.getPageName()).addParameter("keyVal", ref).build();
            HttpGet req = new HttpGet(uri);
            try (final CloseableHttpClient httpClient = HttpClients.createDefault(); final CloseableHttpResponse response = httpClient.execute(req)) {
                final String result = EntityUtils.toString(response.getEntity());
                return responseCacheRepo.save(new ResponseCache(docType, authority, ref, result));
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
