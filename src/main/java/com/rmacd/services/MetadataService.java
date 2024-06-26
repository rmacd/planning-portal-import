package com.rmacd.services;

import com.rmacd.models.AuthorityEnum;
import com.rmacd.models.DocumentDetails;
import com.rmacd.models.PlanningDetails;

import java.util.List;

public interface MetadataService {
    PlanningDetails getPlanningDetails(AuthorityEnum authority, String ref);

    List<PlanningDetails> getCachedPlanningDetails();

    List<DocumentDetails> getDocumentsDetails(AuthorityEnum authority, String ref);

    List<DocumentDetails> getCachedDocumentDetails();
}
