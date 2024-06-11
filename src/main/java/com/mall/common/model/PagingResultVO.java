package com.mall.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingResultVO {
    @Schema(description = "페이징정보", type = "Object")
    private PaginationInfo paginationInfo;

    @Schema(description = "추가정보", type = "Object")
    private Object more;

    @Schema(description = "결과값", type = "Object")
    private Object result;

    public PagingResultVO(PaginationInfo paginationInfo, Object result) {
        this.paginationInfo = paginationInfo;
        this.result = result;
    }

    public PagingResultVO(PaginationInfo paginationInfo, Object more, Object result) {
        this.paginationInfo = paginationInfo;
        this.more = more;
        this.result = result;
    }
}
