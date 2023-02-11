package com.lokuster.operationservice.model;

import com.lokuster.operationservice.util.validation.NoHtml;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(value = "operation_clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationClient extends BaseEntity {
    @Field(value = "_name")
    @Indexed
    @NoHtml
    @NotNull
    @NotBlank
    private String name;

    @Builder
    public OperationClient(String id, String name) {
        super(id);
        this.name = name;
    }
}
