package com.lokuster.operationservice.repository;

import com.lokuster.operationservice.model.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface OperationRepository extends MongoRepository<Operation, String> {
    @Query("{" +
            "'sum' : {'$gte' : ?0, '$lte' : ?1}, " +
            "'_date' : {'$gte' : ?2, '$lte': ?3}," +
            "'_client._name' : {'$regex': ?4}," +
            "'_name._name' : {'$regex': ?5}" +
            "}")
    List<Operation> findAllFiltered(Double minSum, Double maxSum, LocalDate minDate,
                                    LocalDate maxDate, String clientName, String name);
}
