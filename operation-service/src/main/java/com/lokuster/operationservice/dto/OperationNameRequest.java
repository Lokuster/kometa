package com.lokuster.operationservice.dto;

import com.lokuster.operationservice.HasId;
import com.lokuster.operationservice.util.validation.NoHtml;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationNameRequest implements HasId {
    String id;
    @NoHtml
    String name;
}
