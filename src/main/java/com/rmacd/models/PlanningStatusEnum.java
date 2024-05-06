package com.rmacd.models;

public enum PlanningStatusEnum {
    WITHDRAWN("Withdrawn"),
    APPLICATION_REFUSED("Application Refused"),
    AWAITING_ASSESSMENT("Awaiting Assessment");

    final String status;

    PlanningStatusEnum(String status) {
        this.status = status;
    }
}
