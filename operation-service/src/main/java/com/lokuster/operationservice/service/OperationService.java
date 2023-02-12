package com.lokuster.operationservice.service;

import com.lokuster.operationservice.dto.OperationRequest;
import com.lokuster.operationservice.dto.OperationResponse;
import com.lokuster.operationservice.model.Operation;
import com.lokuster.operationservice.repository.OperationClientRepository;
import com.lokuster.operationservice.repository.OperationNameRepository;
import com.lokuster.operationservice.repository.OperationRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.lokuster.operationservice.util.OperationUtil.*;
import static com.lokuster.operationservice.util.validation.ValidationUtil.checkNew;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository repository;
    private final OperationNameRepository nameRepository;
    private final OperationClientRepository clientRepository;

    public List<OperationResponse> getAll(Double minSum, Double maxSum, LocalDate minDate,
                                          LocalDate maxDate, String clientName, String name) {
        return asResponse(repository.findAllFiltered(
                atMinSumOrZero(minSum),
                atMaxSumOrMaxValue(maxSum),
                atMinDateOrMinValue(minDate),
                atMaxDateOrMaxValue(maxDate),
                atRegexOrAnyRegex(clientName),
                atRegexOrAnyRegex(name)
        ));
    }

    public ResponseEntity<OperationResponse> get(String id, HttpServletRequest request) {
        OperationResponse operation = asResponse(repository.findById(id).orElse(null));
        if (operation == null) {
            return null;
        }
        return asResponseEntity(operation, request);
    }

    public ResponseEntity<Operation> create(OperationRequest operationRequest, HttpServletRequest request) {
        checkNew(operationRequest);
        Operation created = repository.insert(
                fromRequest(
                        operationRequest,
                        nameRepository.findByName(operationRequest.getName()),
                        clientRepository.findByName(operationRequest.getClient())
                )
        );
        return asResponseEntity(created, request);
    }

    public ResponseEntity<Void> delete(String id) {
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Operation> save(OperationRequest operation, HttpServletRequest request) {
        Operation updated = repository.save(
                fromRequest(
                        operation,
                        nameRepository.findByName(operation.getName()),
                        clientRepository.findByName(operation.getClient())
                )
        );
        return asResponseEntity(updated, request);
    }
}
