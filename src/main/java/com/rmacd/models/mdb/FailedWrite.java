package com.rmacd.models.mdb;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("failed_writes")
public class FailedWrite {

    String id;
    String document;

    public FailedWrite() {}

    public FailedWrite(String docId, String parentJson) {
        this.id = docId;
        this.document = parentJson;
    }

    public String getId() {
        return id;
    }

    public FailedWrite setId(String id) {
        this.id = id;
        return this;
    }

    public String getDocument() {
        return document;
    }

    public FailedWrite setDocument(String document) {
        this.document = document;
        return this;
    }
}
