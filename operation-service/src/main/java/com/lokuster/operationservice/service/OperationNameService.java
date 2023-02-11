package com.lokuster.operationservice.service;

import com.lokuster.operationservice.dto.OperationNameRequest;
import com.lokuster.operationservice.model.OperationName;
import com.lokuster.operationservice.repository.OperationNameRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.lokuster.operationservice.util.OperationNameUtil.fromRequest;
import static com.lokuster.operationservice.util.OperationUtil.asResponseEntity;
import static com.lokuster.operationservice.util.validation.ValidationUtil.checkNew;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationNameService {
    private final OperationNameRepository repository;

    public ResponseEntity<OperationName> create(OperationNameRequest operationName, HttpServletRequest request) {
        checkNew(operationName);
        OperationName created = repository.insert(fromRequest(operationName));
        return asResponseEntity(created, request);
    }
}
