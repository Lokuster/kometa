package com.lokuster.operationservice.service;

import com.lokuster.operationservice.dto.OperationClientRequest;
import com.lokuster.operationservice.dto.OperationClientResponse;
import com.lokuster.operationservice.model.OperationClient;
import com.lokuster.operationservice.repository.OperationClientRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lokuster.operationservice.util.OperationClientUtil.asResponse;
import static com.lokuster.operationservice.util.OperationClientUtil.fromRequest;
import static com.lokuster.operationservice.util.OperationUtil.asResponseEntity;
import static com.lokuster.operationservice.util.validation.ValidationUtil.checkNew;

@Service
@RequiredArgsConstructor
public class OperationClientService {
    private final OperationClientRepository repository;

    public ResponseEntity<OperationClient> create(OperationClientRequest operationClient, HttpServletRequest request) {
        checkNew(operationClient);
        OperationClient created = repository.insert(fromRequest(operationClient));
        return asResponseEntity(created, request);
    }

    public ResponseEntity<Void> delete(String id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<OperationClientResponse> getAll() {
        return asResponse(repository.findAll());
    }

    public ResponseEntity<OperationClientResponse> get(String id, HttpServletRequest request) {
        OperationClient operationClient = repository.findById(id).orElse(null);
        if (operationClient == null) {
            return null;
        }
        return asResponseEntity(asResponse(operationClient), request);
    }

    public ResponseEntity<OperationClient> save(OperationClientRequest operation, HttpServletRequest request) {
        OperationClient updated = repository.save(fromRequest(operation));
        return asResponseEntity(updated, request);
    }
}
