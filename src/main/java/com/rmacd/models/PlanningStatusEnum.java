package com.rmacd.models;

public enum PlanningStatusEnum {
    AWAITING_ASSESSMENT("Awaiting Assessment");

    final String status;

    PlanningStatusEnum(String status) {
        this.status = status;
    }
}
