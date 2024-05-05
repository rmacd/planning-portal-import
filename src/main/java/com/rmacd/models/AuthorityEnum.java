package com.rmacd.models;

public enum AuthorityEnum {

    // zero-based
    trafford(1, "https://citydev-portal.edinburgh.gov.uk/idoxpa-web/applicationDetails.do"),
    edinburgh(2, "https://citydev-portal.edinburgh.gov.uk/idoxpa-web/applicationDetails.do");

    final int derivedTypeIndex;
    final String detailUrlBase;

    AuthorityEnum(int derivedTypeIndex, String url) {
        this.derivedTypeIndex = derivedTypeIndex;
        this.detailUrlBase = url;
    }

    public int getDerivedTypeIndex() {
        return derivedTypeIndex;
    }

    public String getDetailUrlBase() {
        return detailUrlBase;
    }
}
