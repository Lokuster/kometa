package com.lokuster.operationservice.controller;

import com.lokuster.operationservice.dto.OperationClientRequest;
import com.lokuster.operationservice.dto.OperationClientResponse;
import com.lokuster.operationservice.model.OperationClient;
import com.lokuster.operationservice.service.OperationClientService;
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
@RequestMapping(value = OperationClientController.REST_URL)
@RequiredArgsConstructor
@Slf4j
public class OperationClientController {
    private final OperationClientService service;
    public static final String REST_URL = "/api/operation-clients";

    @GetMapping
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<List<OperationClientResponse>> getAll() {
        log.info("operation client getAll");
        return CompletableFuture.supplyAsync(service::getAll);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<OperationClientResponse>> get(@PathVariable String id,
                                                                          HttpServletRequest request) {
        log.info("operation client get by id {}", id);
        return CompletableFuture.supplyAsync(() -> service.get(id, request));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<OperationClient>> create(@RequestBody @Valid OperationClientRequest operationClient,
                                                                     HttpServletRequest request) {
        log.info("create operation client from request {}", operationClient);
        return CompletableFuture.supplyAsync(() -> service.create(operationClient, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<Void>> delete(@PathVariable String id) {
        log.info("delete operation client with id = {}", id);
        return CompletableFuture.supplyAsync(() -> service.delete(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<OperationClient>> update(@Valid @RequestBody OperationClientRequest operationClient,
                                                                     @PathVariable String id,
                                                                     HttpServletRequest request) {
        log.info("update operation {} with id {}", operationClient, id);
        assureIdConsistent(operationClient, id);
        return CompletableFuture.supplyAsync(() -> service.save(operationClient, request));
    }
}
