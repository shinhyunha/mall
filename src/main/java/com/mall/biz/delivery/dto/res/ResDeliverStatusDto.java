package com.mall.biz.delivery.dto.res;

import com.mall.biz.delivery.entity.DeliveryCode;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResDeliverStatusDto {
    @Schema(description = "배송상태 번호")
    private Long deliveryStatusNo;

    @Schema(description = "배송 번호")
    private Long deliveryNo;

    @Schema(description = "배송상태코드 (공통코드 [10004] , 배송생성[CRT], 배송중[ING], 배송완료[COM], 배송취소[CAN])")
    private DeliveryCode deliveryCode;

    @Schema(description = "배송상태명 (공통코드 [10004] , 배송생성[CRT], 배송중[ING], 배송완료[COM], 배송취소[CAN])")
    private String deliveryName;

    @Schema(description = "배송상태 생성일")
    private LocalDateTime createDate;

    @QueryProjection
    public ResDeliverStatusDto(Long deliveryStatusNo, Long deliveryNo, DeliveryCode deliveryCode, LocalDateTime createDate) {
        this.deliveryStatusNo = deliveryStatusNo;
        this.deliveryNo = deliveryNo;
        this.deliveryCode = deliveryCode;
        this.createDate = createDate;
    }
}
