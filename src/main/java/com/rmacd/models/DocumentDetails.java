package com.rmacd.models;

import java.time.LocalDate;

public class DocumentDetails {

    String id;
    String attachmentId;
    AuthorityEnum authority;
    String keyVal;
    LocalDate datePublished;
    String documentType;
    String description;
    String url;

    @Override
    public String toString() {
        return "DocumentDetails{" +
                "id='" + id + '\'' +
                ", attachmentId='" + attachmentId + '\'' +
                ", authority=" + authority +
                ", keyVal='" + keyVal + '\'' +
                ", datePublished=" + datePublished +
                ", documentType='" + documentType + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public AuthorityEnum getAuthority() {
        return authority;
    }

    public DocumentDetails setAuthority(AuthorityEnum authority) {
        this.authority = authority;
        return this;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public DocumentDetails setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
        return this;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public DocumentDetails setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DocumentDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DocumentDetails setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDocumentType() {
        return documentType;
    }

    public DocumentDetails setDocumentType(String documentType) {
        this.documentType = documentType;
        return this;
    }

    public String getId() {
        return id;
    }

    public DocumentDetails setId(String id) {
        this.id = id;
        return this;
    }

    public String getKeyVal() {
        return keyVal;
    }

    public DocumentDetails setKeyVal(String keyVal) {
        this.keyVal = keyVal;
        return this;
    }
}
