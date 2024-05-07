package com.rmacd.models;

public enum AppealStatusEnum {
    NO_REMIT("No remit"),
    NOT_UPHOLD_APPLICATION_GRANTED("Not upheld: Application granted"),
    APPEAL_DISMISSED_PERMISSION_REFUSED("Appeal dismissed: Permission refused"),
    APPEAL_ALLOWED_PERMISSION_GRANTED("Appeal allowed: Permission granted"),
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
