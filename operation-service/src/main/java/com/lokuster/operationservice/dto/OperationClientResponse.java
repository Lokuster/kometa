package com.lokuster.operationservice.dto;

import com.lokuster.operationservice.HasId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationClientResponse implements HasId {
    String id;
    String name;
}
