package com.mall.common.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    @CreatedBy
    @Column(updatable = false)
    private String createdBy = "Tester";

    @LastModifiedBy
    private String lastModifiedBy = "Modifier";

    public void updateBaseEntity() {
        this.lastModifiedDate = LocalDateTime.now();
        this.lastModifiedBy = "lastModifer";
    }
}

