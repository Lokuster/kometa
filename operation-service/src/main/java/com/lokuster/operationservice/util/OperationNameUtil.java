package com.lokuster.operationservice.util;

import com.lokuster.operationservice.dto.OperationNameRequest;
import com.lokuster.operationservice.model.OperationName;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OperationNameUtil {
    public static OperationName fromRequest(OperationNameRequest operationName) {
        return OperationName.builder()
                .id(operationName.getId())
                .name(operationName.getName())
                .build();
    }
}
