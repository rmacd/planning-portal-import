package com.rmacd.models;

public enum PlanningStatusEnum {
    WITHDRAWN("Withdrawn"),
    APPEAL_LODGED("Appeal lodged"),
    APPLICATION_GRANTED("Application Granted"),
    AWAITING_DECISION("Awaiting Decision"),
    APPLICATION_REFUSED("Application Refused"),
    AWAITING_ASSESSMENT("Awaiting Assessment");

    final String status;

    PlanningStatusEnum(String status) {
        this.status = status;
    }
}
