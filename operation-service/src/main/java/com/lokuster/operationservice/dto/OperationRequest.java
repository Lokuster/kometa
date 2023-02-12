package com.lokuster.operationservice.dto;

import com.lokuster.operationservice.HasId;
import com.lokuster.operationservice.util.validation.NoHtml;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationRequest implements HasId {
    private String id;
    private LocalDate date;
    @NoHtml
    private String document;
    @NoHtml
    private String client;
    @NoHtml
    private String name;
    @NoHtml
    private String type;
    private Double sum;
    @NoHtml
    private String description;
}
