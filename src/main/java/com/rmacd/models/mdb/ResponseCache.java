package com.rmacd.models.mdb;

import com.rmacd.models.AuthorityEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "response_cache")
public class ResponseCache {

    String id;
    String document;
    LocalDate lastUpdate = LocalDate.now();

    public String getId() {
        return id;
    }

    public ResponseCache setId(String id) {
        this.id = id;
        return this;
    }

    public String getDocument() {
        return document;
    }

    public ResponseCache setDocument(String document) {
        this.document = document;
        return this;
    }

    public ResponseCache(String id, String document) {
        this.id = id;
        this.document = document;
    }


    public static String generateDetailsId(AuthorityEnum authorityEnum, String ref) {
        return "details_%s_%s".formatted(authorityEnum, ref);
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }
}
