package com.lokuster.operationservice.service;

import com.lokuster.operationservice.dto.OperationNameRequest;
import com.lokuster.operationservice.dto.OperationNameResponse;
import com.lokuster.operationservice.model.OperationName;
import com.lokuster.operationservice.repository.OperationNameRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lokuster.operationservice.util.OperationNameUtil.asResponse;
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

    public List<OperationNameResponse> getAll() {
        return asResponse(repository.findAll());
    }

    public ResponseEntity<OperationNameResponse> get(String id, HttpServletRequest request) {
        OperationName operationName = repository.findById(id).orElse(null);
        if (operationName == null) {
            return null;
        }
        return asResponseEntity(asResponse(operationName), request);
    }

    public ResponseEntity<Void> delete(String id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<OperationName> save(OperationNameRequest operationName, HttpServletRequest request) {
        OperationName updated = repository.save(fromRequest(operationName));
        return asResponseEntity(updated, request);
    }
}
