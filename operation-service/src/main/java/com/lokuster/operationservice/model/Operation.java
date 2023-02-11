package com.lokuster.operationservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(value = "operations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Operation extends BaseEntity {
    @NotNull
    @Field(value = "_date")
    private LocalDate date;
    @NotNull
    @NotBlank
    @Field(value = "_document")
    private String document;
    @Field(value = "_client")
    @NotNull
    private OperationClient client;
    @Field(value = "_name")
    @NotNull
    private OperationName name;
    @Field(value = "_type")
    @NotNull
    private OperationType type;
    @NotNull
    @Positive
    private Double sum;
    @Range(max = 256)
    private String description;

    @Builder
    public Operation(String id, LocalDate date, String document, OperationClient client, OperationName name, OperationType type, Double sum, String description) {
        super(id);
        this.date = date;
        this.document = document;
        this.client = client;
        this.name = name;
        this.type = type;
        this.sum = sum;
        this.description = description;
    }
}
