package com.rmacd.repos.mdb;

import com.rmacd.models.mdb.FailedWrite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FailedWriteRepo extends MongoRepository<FailedWrite, String> {
}
