package com.mall.biz.order.dto;

import com.mall.biz.order.entity.OrderCode;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderStatusDto {
    @Schema(description = "주문상태번호")
    private Long orderStatusNo;

    @Schema(description = "주문번호")
    private Long orderNo;

    @Schema(description = "주문상태명 (공통코드 [10003] , 주문생성[CRT], 주문완료[COM], 주문취소[CAN])")
    private OrderCode orderCode;

    @Schema(description = "주문상태명 (공통코드 [10003] , 주문생성[CRT], 주문완료[COM], 주문취소[CAN])")
    private String orderStatusName;

    @Schema(description = "생성일")
    private LocalDateTime createDate;

    @QueryProjection
    public OrderStatusDto(Long orderStatusNo, LocalDateTime createDate, OrderCode orderCode, Long orderNo) {
        this.orderStatusNo = orderStatusNo;
        this.createDate = createDate;
        this.orderCode = orderCode;
        this.orderNo = orderNo;
    }
}
