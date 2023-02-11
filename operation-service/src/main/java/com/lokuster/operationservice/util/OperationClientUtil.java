package com.lokuster.operationservice.util;

import com.lokuster.operationservice.dto.OperationClientRequest;
import com.lokuster.operationservice.dto.OperationClientResponse;
import com.lokuster.operationservice.model.OperationClient;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class OperationClientUtil {
    public static OperationClient fromRequest(OperationClientRequest operationClient) {
        return OperationClient.builder()
                .id(operationClient.getId())
                .name(operationClient.getName())
                .build();
    }

    public static OperationClientResponse asResponse(OperationClient operationClient) {
        return OperationClientResponse.builder()
                .id(operationClient.getId())
                .name(operationClient.getName())
                .build();
    }

    public static List<OperationClientResponse> asResponse(List<OperationClient> operationClients) {
        return operationClients.stream().map(OperationClientUtil::asResponse).collect(Collectors.toList());
    }
}
