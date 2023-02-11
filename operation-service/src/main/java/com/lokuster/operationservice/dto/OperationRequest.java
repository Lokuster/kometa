package com.lokuster.operationservice.dto;

import com.lokuster.operationservice.HasId;
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
    private String document;
    private String client;
    private String name;
    private String type;
    private Double sum;
    private String description;
}
