package com.rmacd.models;

public enum DecisionLevelEnum {

    LOCAL_DELEGATED("Local Delegated Decision");

    final String decisionLevel;

    DecisionLevelEnum(String decisionLevel) {
        this.decisionLevel = decisionLevel;
    }

    public String getDecisionLevel() {
        return decisionLevel;
    }
}
