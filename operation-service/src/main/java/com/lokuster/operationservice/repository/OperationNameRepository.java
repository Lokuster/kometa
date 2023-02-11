package com.lokuster.operationservice.repository;

import com.lokuster.operationservice.model.OperationName;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OperationNameRepository extends MongoRepository<OperationName, String> {
    OperationName findByName(String name);
}
