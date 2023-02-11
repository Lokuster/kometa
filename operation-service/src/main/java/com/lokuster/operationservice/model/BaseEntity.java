package com.lokuster.operationservice.model;

import com.lokuster.operationservice.HasId;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity implements HasId {
    @Id
    private String id;
    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public String id() {
        Assert.notNull(id, "Entity must have id");
        return id;
    }
}
