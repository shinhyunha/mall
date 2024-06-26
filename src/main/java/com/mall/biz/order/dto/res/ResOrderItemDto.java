package com.mall.biz.order.dto.res;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResOrderItemDto {
    @Schema(description = "주문상품 번호")
    private Long orderItemNo;

    @Schema(description = "주문 번호")
    private Long orderNo;

    @Schema(description = "상품 번호")
    private Long itemNo;

    @Schema(description = "상품 건별 가격")
    private int itemPrice;

    @Schema(description = "상품 개수")
    private int orderQuantity;

    @Schema(description = "상품 전체 금액")
    private int totalPrice;

    @Schema(description = "상품 구매 순서")
    private int sequence;

    @QueryProjection

    public ResOrderItemDto(Long orderItemNo, Long orderNo, Long itemNo, int itemPrice, int orderQuantity, int totalPrice, int sequence) {
        this.orderItemNo = orderItemNo;
        this.orderNo = orderNo;
        this.itemNo = itemNo;
        this.itemPrice = itemPrice;
        this.orderQuantity = orderQuantity;
        this.totalPrice = totalPrice;
        this.sequence = sequence;
    }
}
