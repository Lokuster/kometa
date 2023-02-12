package com.lokuster.operationservice.controller;

import com.lokuster.operationservice.dto.OperationRequest;
import com.lokuster.operationservice.dto.OperationResponse;
import com.lokuster.operationservice.model.Operation;
import com.lokuster.operationservice.service.OperationService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.lokuster.operationservice.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = OperationController.REST_URL)
@RequiredArgsConstructor
@Slf4j
public class OperationController {
    public static final String REST_URL = "/api/operations";
    private final OperationService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<List<OperationResponse>> getAll(@RequestParam(name = "minSum", required = false) @Nullable Double minSum,
                                                             @RequestParam(name = "maxSum", required = false) @Nullable Double maxSum,
                                                             @RequestParam(name = "minDate", required = false) @Nullable LocalDate minDate,
                                                             @RequestParam(name = "maxDate", required = false) @Nullable LocalDate maxDate,
                                                             @RequestParam(name = "clientName", required = false) @Nullable String clientName,
                                                             @RequestParam(name = "name", required = false) @Nullable String name) {
        log.info("operation getAll");
        return CompletableFuture.supplyAsync(() -> service.getAll(minSum, maxSum, minDate, maxDate, clientName, name));
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<OperationResponse>> get(@PathVariable String id,
                                                                    HttpServletRequest request) {
        log.info("operation get by id {}", id);
        return CompletableFuture.supplyAsync(() -> service.get(id, request));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<Operation>> create(@RequestBody @Valid OperationRequest operation,
                                                               HttpServletRequest request) {
        log.info("create operation from request {}", operation);
        return CompletableFuture.supplyAsync(() -> service.create(operation, request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<Void>> delete(@PathVariable String id) {
        log.info("delete operation with id = {}", id);
        return CompletableFuture.supplyAsync(() -> service.delete(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CircuitBreaker(name = "operation")
    @TimeLimiter(name = "operation")
    @Retry(name = "operation")
    public CompletableFuture<ResponseEntity<Operation>> update(@Valid @RequestBody OperationRequest operation,
                                                               @PathVariable String id,
                                                               HttpServletRequest request) {
        log.info("update operation {} with id {}", operation, id);
        assureIdConsistent(operation, id);
        return CompletableFuture.supplyAsync(() -> service.save(operation, request));
    }
}
