package com.mall.biz.order.dto;

import com.mall.biz.order.entity.OrderCode;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderPurchaserDto {
    @Schema(description = "주문자 번호")
    private Long orderPurchaserNo;

    @Schema(description = "주문번호")
    private Long orderNo;

    @Schema(description = "회원번호")
    private String memberId;

    @Schema(description = "주문자 이름")
    private String name;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "주문자 번호")
    private String phone;

    @Schema(description = "생성일")
    private LocalDateTime createDate;

    @QueryProjection
    public OrderPurchaserDto(Long orderPurchaserNo, Long orderNo, String memberId, String name, String email, String phone, LocalDateTime createDate) {
        this.orderPurchaserNo = orderPurchaserNo;
        this.orderNo = orderNo;
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.createDate = createDate;
    }
}
