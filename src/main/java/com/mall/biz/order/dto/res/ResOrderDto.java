package com.mall.biz.order.dto.res;

import com.mall.biz.delivery.dto.res.ResDeliveryDto;
import com.mall.biz.order.dto.OrderPurchaserDto;
import com.mall.biz.order.dto.OrderStatusDto;
import com.mall.biz.order.dto.req.ResOrderPurchaserDto;
import com.mall.biz.order.entity.OrderCode;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class ResOrderDto {
    @Schema(description = "주문번호")
    private Long orderNo;

    @Schema(description = "주문상태명 (공통코드 [10003] , 주문생성[CRT], 주문완료[COM], 주문취소[CAN])")
    private OrderCode orderCode;

    @Schema(description = "주문상태명 (공통코드 [10003] , 주문생성[CRT], 주문완료[COM], 주문취소[CAN])")
    private String orderStatusName;

    @Schema(description = "전체 주문 금액")
    private int totalPrice;

    @Schema(description = "주문상태 전체 목록")
    private List<OrderStatusDto> orderStatusDtoList;

    @Schema(description = "주문 배송 정보")
    private ResDeliveryDto deliveryDto;

    @Schema(description = "주문자 정보")
    private OrderPurchaserDto orderPurchaserDto;

    @Schema(description = "주문 상품 정보")
    private List<ResOrderItemDto> orderItemDtoList;

    @QueryProjection
    public ResOrderDto(Long orderNo, OrderCode orderCode, int totalPrice) {
        this.orderNo = orderNo;
        this.orderCode = orderCode;
        this.totalPrice = totalPrice;
    }
}
