package com.mall.biz.order.dto.res;

import com.mall.biz.delivery.dto.res.ResDeliveryDto;
import com.mall.biz.delivery.entity.DeliveryCode;
import com.mall.biz.order.dto.OrderPurchaserDto;
import com.mall.biz.order.dto.OrderStatusDto;
import com.mall.biz.order.entity.OrderCode;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
public class ResOrderListDto {
    @Schema(description = "주문번호")
    private Long orderNo;

    @Schema(description = "주문상태명 (공통코드 [10003] , 주문생성[CRT], 주문완료[COM], 주문취소[CAN])")
    private OrderCode orderCode;

    @Schema(description = "주문상태명 (공통코드 [10003] , 주문생성[CRT], 주문완료[COM], 주문취소[CAN])")
    private String orderStatusName;

    @Schema(description = "전체 주문 금액")
    private int totalPrice;

    @Schema(description = "상품 번호")
    private Long itemNo;

    @Schema(description = "상품이름")
    private String itemName;

    @Schema(description = "주문자 이름")
    private String name;

    @Schema(description = "주문자 번호")
    private Long orderPurchaserNo;

    @Schema(description = "배송상태코드 (공통코드 [10004] , 배송생성[CRT], 배송중[ING], 배송완료[COM], 배송취소[CAN])")
    private DeliveryCode deliveryCode;

    @Schema(description = "배송상태명 (공통코드 [10004] , 배송생성[CRT], 배송중[ING], 배송완료[COM], 배송취소[CAN])")
    private String deliveryName;

    @Schema(description = "주문생성일")
    private LocalDateTime createDate;

    @QueryProjection
    public ResOrderListDto(Long orderNo, OrderCode orderCode, int totalPrice, Long itemNo, String itemName, String name, Long orderPurchaserNo, DeliveryCode deliveryCode, LocalDateTime createDate) {
        this.orderNo = orderNo;
        this.orderCode = orderCode;
        this.totalPrice = totalPrice;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.name = name;
        this.orderPurchaserNo = orderPurchaserNo;
        this.deliveryCode = deliveryCode;
        this.createDate = createDate;
    }
}
