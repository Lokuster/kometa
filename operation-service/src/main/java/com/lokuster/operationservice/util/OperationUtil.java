package com.lokuster.operationservice.util;

import com.lokuster.operationservice.HasId;
import com.lokuster.operationservice.dto.OperationRequest;
import com.lokuster.operationservice.dto.OperationResponse;
import com.lokuster.operationservice.model.Operation;
import com.lokuster.operationservice.model.OperationClient;
import com.lokuster.operationservice.model.OperationName;
import com.lokuster.operationservice.model.OperationType;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.lokuster.operationservice.controller.OperationController.REST_URL;

@UtilityClass
public class OperationUtil {
    private static final LocalDate MAX_DATE = LocalDate.of(10000, 1, 1);
    private static final LocalDate MIN_DATE = LocalDate.of(0, 1, 1);

    public static OperationResponse asResponse(Operation operation) {
        if (operation == null) {
            return null;
        }
        return OperationResponse.builder()
                .id(operation.getId())
                .name(operation.getName().getName())
                .client(operation.getClient().getName())
                .document(operation.getDocument())
                .description(operation.getDescription())
                .income(operation.getType() == OperationType.INCOME ? operation.getSum() : null)
                .expense(operation.getType() == OperationType.EXPENSE ? operation.getSum() : null)
                .date(operation.getDate())
                .build();
    }

    public static List<OperationResponse> asResponse(List<Operation> operations) {
        return operations.stream()
                .map(OperationUtil::asResponse)
                .collect(Collectors.toList());
    }


    public static Operation fromRequest(OperationRequest operation, OperationName name, OperationClient client) {
        return Operation.builder()
                .id(operation.getId())
                .name(name)
                .client(client)
                .type(operation.getType().equals("Доход") ? OperationType.INCOME : OperationType.EXPENSE)
                .document(operation.getDocument())
                .description(operation.getDescription())
                .sum(operation.getSum())
                .date(operation.getDate())
                .build();
    }

    public static <T extends HasId> ResponseEntity<T> asResponseEntity(T operation, HttpServletRequest request) {
        URI uriOfNewResource = ServletUriComponentsBuilder.fromRequestUri(request)
                .path(REST_URL + "/{id}")
                .buildAndExpand(operation.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(operation);
    }

    public static Double atMinSumOrZero(Double minSum) {
        return minSum != null ? minSum : 0;
    }

    public static Double atMaxSumOrMaxValue(Double maxSum) {
        return maxSum != null ? maxSum : Double.MAX_VALUE;
    }

    public static LocalDate atMinDateOrMinValue(LocalDate minDate) {
        return minDate != null ? minDate : MIN_DATE;
    }

    public static LocalDate atMaxDateOrMaxValue(LocalDate maxDate) {
        return maxDate != null ? maxDate : MAX_DATE;
    }

    public static String atRegexOrAnyRegex(String regex) {
        return regex != null ? regex : ".*";
    }
}
