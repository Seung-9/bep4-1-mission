package com.back.global.jpa.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class BaseAndTimeManual extends BaseEntity {
    @Id
    private int id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
