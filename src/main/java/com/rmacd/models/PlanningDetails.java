package com.rmacd.models;

import java.time.LocalDate;

public class PlanningDetails {

    String reference;
    LocalDate validated;
    String address;
    String proposal;
    PlanningStatusEnum status;
    AppealStatusEnum appealStatus;

    public String getApplicationType() {
        return applicationType;
    }

    public PlanningDetails setApplicationType(String applicationType) {
        this.applicationType = applicationType;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public PlanningDetails setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public LocalDate getValidated() {
        return validated;
    }

    public PlanningDetails setValidated(LocalDate validated) {
        this.validated = validated;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PlanningDetails setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getProposal() {
        return proposal;
    }

    public PlanningDetails setProposal(String proposal) {
        this.proposal = proposal;
        return this;
    }

    public PlanningStatusEnum getStatus() {
        return status;
    }

    public PlanningDetails setStatus(PlanningStatusEnum status) {
        this.status = status;
        return this;
    }

    public AppealStatusEnum getAppealStatus() {
        return appealStatus;
    }

    public PlanningDetails setAppealStatus(AppealStatusEnum appealStatus) {
        this.appealStatus = appealStatus;
        return this;
    }

    public String getDecisionLevel() {
        return decisionLevel;
    }

    public PlanningDetails setDecisionLevel(String decisionLevel) {
        this.decisionLevel = decisionLevel;
        return this;
    }

    // further information
    String applicationType;
    String decisionLevel;
    String communityCouncil;
    String ward;
    String applicantName;
    String agentName;
    String agentCompanyName;
    String agentAddress;
    String phone;
    String email;
    LocalDate applicationReceived;

    public String getCommunityCouncil() {
        return communityCouncil;
    }

    public PlanningDetails setCommunityCouncil(String communityCouncil) {
        this.communityCouncil = communityCouncil;
        return this;
    }

    public String getWard() {
        return ward;
    }

    public PlanningDetails setWard(String ward) {
        this.ward = ward;
        return this;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public PlanningDetails setApplicantName(String applicantName) {
        this.applicantName = applicantName;
        return this;
    }

    public String getAgentName() {
        return agentName;
    }

    public PlanningDetails setAgentName(String agentName) {
        this.agentName = agentName;
        return this;
    }

    public String getAgentCompanyName() {
        return agentCompanyName;
    }

    public PlanningDetails setAgentCompanyName(String agentCompanyName) {
        this.agentCompanyName = agentCompanyName;
        return this;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public PlanningDetails setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PlanningDetails setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PlanningDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getApplicationReceived() {
        return applicationReceived;
    }

    public PlanningDetails setApplicationReceived(LocalDate applicationReceived) {
        this.applicationReceived = applicationReceived;
        return this;
    }

    public PlanningDetails() {}

    @Override
    public String toString() {
        return "PlanningDetails{" +
                "reference='" + reference + '\'' +
                ", status=" + status +
                ", appealStatus=" + appealStatus +
                ", applicationReceived=" + applicationReceived +
                '}';
    }
}
