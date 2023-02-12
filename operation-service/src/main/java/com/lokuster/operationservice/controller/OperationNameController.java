package com.lokuster.operationservice.controller;

import com.lokuster.operationservice.dto.OperationNameRequest;
import com.lokuster.operationservice.dto.OperationNameResponse;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.lokuster.operationservice.util.validation.ValidationUtil.assureIdConsistent;

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

    @GetMapping
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<List<OperationNameResponse>> getAll() {
        log.info("operation name getAll");
        return CompletableFuture.supplyAsync(service::getAll);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<OperationNameResponse>> get(@PathVariable String id,
                                                                        HttpServletRequest request) {
        log.info("operation client get by id {}", id);
        return CompletableFuture.supplyAsync(() -> service.get(id, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<Void>> delete(@PathVariable String id) {
        log.info("delete operation name with id = {}", id);
        return CompletableFuture.supplyAsync(() -> service.delete(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<OperationName>> update(@Valid @RequestBody OperationNameRequest operationName,
                                                                   @PathVariable String id,
                                                                   HttpServletRequest request) {
        log.info("update operation name {} with id {}", operationName, id);
        assureIdConsistent(operationName, id);
        return CompletableFuture.supplyAsync(() -> service.save(operationName, request));
    }
}
