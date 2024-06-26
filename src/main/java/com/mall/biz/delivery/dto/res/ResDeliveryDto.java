package com.mall.biz.delivery.dto.res;

import com.mall.biz.delivery.entity.Delivery;
import com.mall.biz.delivery.entity.DeliveryCode;
import com.mall.biz.item.dto.res.ResItemDto;
import com.mall.biz.order.dto.res.ResOrderItemDto;
import com.mall.biz.order.entity.Order;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class ResDeliveryDto {
    @Schema(description = "배송번호")
    private Long deliveryNo;

    @Schema(description = "주문번호")
    private Long orderNo;

    @Schema(description = "주문금액")
    private int totalPrice;

    @Schema(description = "배송상품 목록")
    private List<ResOrderItemDto> deliveryItemList;

    @Schema(description = "배송상태코드 (공통코드 [10004] , 배송생성[CRT], 배송중[ING], 배송완료[COM], 배송취소[CAN])")
    private DeliveryCode deliveryCode;

    @Schema(description = "배송상태명 (공통코드 [10004] , 배송생성[CRT], 배송중[ING], 배송완료[COM], 배송취소[CAN])")
    private String deliveryName;

    @Schema(description = "수신인 이름")
    private String receiverName;

    @Schema(description = "배송지 주소")
    private String deliveryAddress;

    @Schema(description = "배송지 우편번호")
    private String zipCode;

    @Schema(description = "연락처")
    private String phone;

    @QueryProjection
    public ResDeliveryDto(Long deliveryNo, Long orderNo, int totalPrice, DeliveryCode deliveryCode, String receiverName, String deliveryAddress, String zipCode, String phone) {
        this.deliveryNo = deliveryNo;
        this.orderNo = orderNo;
        this.totalPrice = totalPrice;
        this.deliveryCode = deliveryCode;
        this.receiverName = receiverName;
        this.deliveryAddress = deliveryAddress;
        this.zipCode = zipCode;
        this.phone = phone;
    }
}
