package com.mall.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "group_code_detail")
public class GroupCodeDetailEntity {
    @Id
    @Column(name = "detail_code_id")
    private Long id;

    private String groupCode;

    private String detailCode;
    private String code_data;
    private int sequence;
    private String description;
}
