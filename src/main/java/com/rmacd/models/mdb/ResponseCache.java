package com.rmacd.models.mdb;

import com.rmacd.models.AuthorityEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "response_cache")
public class ResponseCache {

    ResourceTypeEnum type;
    String id;
    String ref;
    AuthorityEnum authority;
    String document;
    LocalDate lastUpdate = LocalDate.now();

    public ResponseCache(ResourceTypeEnum type, String id, String ref, String document) {
        this.type = type;
        this.id = id;
        this.ref = ref;
        this.document = document;
    }

    @Override
    public String toString() {
        return "ResponseCache{" +
                "type=" + type +
                ", id='" + id + '\'' +
                ", ref='" + ref + '\'' +
                ", authority=" + authority +
                ", document='" + document + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    public AuthorityEnum getAuthority() {
        return authority;
    }

    public ResponseCache setAuthority(AuthorityEnum authority) {
        this.authority = authority;
        return this;
    }

    public String getRef() {
        return ref;
    }

    public ResponseCache setRef(String ref) {
        this.ref = ref;
        return this;
    }

    public ResourceTypeEnum getType() {
        return type;
    }

    public ResponseCache setType(ResourceTypeEnum type) {
        this.type = type;
        return this;
    }

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

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public ResponseCache setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

}
