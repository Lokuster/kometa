package com.lokuster.operationservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.Assert;

public interface HasId {
    String getId();

    void setId(String id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    default String id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
