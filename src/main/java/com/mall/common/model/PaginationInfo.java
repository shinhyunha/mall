package com.mall.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationInfo {
    private int number;           // 현재 페이지 번호
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private long totalCount;

    public PaginationInfo() {
        this.number = 1;
        this.recordSize = 10;
    }
}
