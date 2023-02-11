package com.lokuster.operationservice.controller;

import com.lokuster.operationservice.dto.OperationNameRequest;
import com.lokuster.operationservice.model.OperationName;
import com.lokuster.operationservice.service.OperationNameService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = OperationNameController.REST_URL)
@RequiredArgsConstructor
@Slf4j
public class OperationNameController {
    private final OperationNameService service;
    public static final String REST_URL = "/api/operation-names";

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<OperationName>> create(@RequestBody @Valid OperationNameRequest operationName,
                                                                   HttpServletRequest request) {
        log.info("create operation name from request {}", operationName);
        return CompletableFuture.supplyAsync(() -> service.create(operationName, request));
    }
}
