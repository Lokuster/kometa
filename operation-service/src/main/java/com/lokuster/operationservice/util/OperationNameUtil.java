package com.lokuster.operationservice.util;

import com.lokuster.operationservice.dto.OperationNameRequest;
import com.lokuster.operationservice.dto.OperationNameResponse;
import com.lokuster.operationservice.model.OperationName;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class OperationNameUtil {
    public static OperationName fromRequest(OperationNameRequest operationName) {
        return OperationName.builder()
                .id(operationName.getId())
                .name(operationName.getName())
                .build();
    }

    public static OperationNameResponse asResponse(OperationName operationName) {
        return OperationNameResponse.builder()
                .id(operationName.getId())
                .name(operationName.getName())
                .build();
    }

    public static List<OperationNameResponse> asResponse(List<OperationName> operationNames) {
        return operationNames.stream().map(OperationNameUtil::asResponse).collect(Collectors.toList());
    }
}
