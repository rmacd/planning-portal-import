package com.rmacd.models;

public enum AppealStatusEnum {

    UPHOLD_APPLICATION_REFUSED("Uphold: Application Refused"),
    APPEAL_OR_REVIEW_IN_PROGRESS("Appeal or review in progress"),
    UNKNOWN("Unknown");

    final String appealStatus;

    AppealStatusEnum(String appealStatus) {
        this.appealStatus = appealStatus;
    }

    public String getAppealStatus() {
        return appealStatus;
    }

}
