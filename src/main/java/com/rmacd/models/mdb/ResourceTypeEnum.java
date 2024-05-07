package com.rmacd.models.mdb;

import com.rmacd.models.AuthorityEnum;

public enum ResourceTypeEnum {

    DETAILS("printPreview"), DOCUMENTS("documents");

    final String pageName;

    ResourceTypeEnum(String pageName) {
        this.pageName = pageName;
    }

    public String getPageName() {
        return pageName;
    }

    public String generateId(AuthorityEnum authorityEnum, String ref) {
        return "%s_%s_%s".formatted(this.name().toLowerCase(), authorityEnum, ref);
    }
}
