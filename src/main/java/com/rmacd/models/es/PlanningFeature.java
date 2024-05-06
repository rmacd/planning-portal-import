package com.rmacd.models.es;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.rmacd.models.AuthorityEnum;

public class PlanningFeature {

    @JsonSetter("object_id")
    String objectId;
    String refval;
    String keyval;
    AuthorityEnum authority;

    public AuthorityEnum getAuthority() {
        return authority;
    }

    public PlanningFeature setAuthority(AuthorityEnum authority) {
        this.authority = authority;
        return this;
    }

    public String getObjectId() {
        return objectId;
    }

    public PlanningFeature setObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public String getRefval() {
        return refval;
    }

    public PlanningFeature setRefval(String refval) {
        this.refval = refval;
        return this;
    }

    public String getKeyval() {
        return keyval;
    }

    public PlanningFeature setKeyval(String keyval) {
        this.keyval = keyval;
        return this;
    }
}
