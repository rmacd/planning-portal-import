package com.rmacd.models;

public enum AppealStatusEnum {

    UNKNOWN("Unknown");

    final String appealStatus;

    AppealStatusEnum(String appealStatus) {
        this.appealStatus = appealStatus;
    }

    public String getAppealStatus() {
        return appealStatus;
    }

}
