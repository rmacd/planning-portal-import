package com.rmacd.endpoints;

import com.rmacd.models.AuthorityEnum;
import com.rmacd.models.PlanningDetails;
import com.rmacd.services.MetadataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetadataManager {

    final MetadataService metadataService;

    public MetadataManager(MetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping("/details/{authority}/{ref}")
    public PlanningDetails getDetails(@PathVariable AuthorityEnum authority, @PathVariable String ref) {
        return metadataService.getPlanningDetails(authority, ref);
    }

}
