package com.rmacd.repos.mdb;

import com.rmacd.models.mdb.ResponseCache;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResponseCacheRepo extends MongoRepository<ResponseCache, String> {
}
