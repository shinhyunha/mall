package com.mall.biz.delivery.dto.res;

import com.mall.biz.delivery.entity.DeliveryCode;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class ResDeliveryListDto {
    @Schema(description = "배송번호")
    private Long deliveryNo;

    @Schema(description = "주문번호")
    private Long orderNo;

    @Schema(description = "주문금액")
    private int totalPrice;

    @Schema(description = "대표 상품번호")
    private Long itemNo;

    @Schema(description = "대표 상품명")
    private String itemName;

    @Schema(description = "배송상태코드 (공통코드 [10004] , 배송생성[CRT], 배송중[ING], 배송완료[COM], 배송취소[CAN])")
    private DeliveryCode deliveryCode;

    @Schema(description = "배송상태명 (공통코드 [10004] , 배송생성[CRT], 배송중[ING], 배송완료[COM], 배송취소[CAN])")
    private String deliveryName;

    @QueryProjection
    public ResDeliveryListDto(Long deliveryNo, Long orderNo, int totalPrice, Long itemNo, String itemName, DeliveryCode deliveryCode) {
        this.deliveryNo = deliveryNo;
        this.orderNo = orderNo;
        this.totalPrice = totalPrice;
        this.itemNo = itemNo;
        this.itemName = itemName;
        this.deliveryCode = deliveryCode;
    }
}
