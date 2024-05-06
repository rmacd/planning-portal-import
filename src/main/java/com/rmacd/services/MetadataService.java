package com.rmacd.services;

import com.rmacd.models.AuthorityEnum;
import com.rmacd.models.PlanningDetails;

public interface MetadataService {
    PlanningDetails getPlanningDetails(AuthorityEnum authority, String ref);
}
