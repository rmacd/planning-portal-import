package com.rmacd.repos.mdb;

import com.rmacd.models.mdb.ResourceTypeEnum;
import com.rmacd.models.mdb.ResponseCache;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResponseCacheRepo extends MongoRepository<ResponseCache, String> {

    List<ResponseCache> findByType(ResourceTypeEnum resourceTypeEnum);

}
