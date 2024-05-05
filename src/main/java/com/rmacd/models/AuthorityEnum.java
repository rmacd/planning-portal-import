package com.rmacd.models;

public enum AuthorityEnum {

    // zero-based
    trafford(1), edinburgh(2);

    final int derivedTypeIndex;

    AuthorityEnum(int derivedTypeIndex) {
        this.derivedTypeIndex = derivedTypeIndex;
    }

    public int getDerivedTypeIndex() {
        return derivedTypeIndex;
    }
}
