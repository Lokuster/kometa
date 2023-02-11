package com.lokuster.operationservice.repository;

import com.lokuster.operationservice.model.OperationClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OperationClientRepository extends MongoRepository<OperationClient, String> {
    OperationClient findByName(String name);
}
