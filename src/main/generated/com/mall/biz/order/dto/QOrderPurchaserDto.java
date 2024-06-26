package com.mall.biz.order.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mall.biz.order.dto.QOrderPurchaserDto is a Querydsl Projection type for OrderPurchaserDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderPurchaserDto extends ConstructorExpression<OrderPurchaserDto> {

    private static final long serialVersionUID = -1869274107L;

    public QOrderPurchaserDto(com.querydsl.core.types.Expression<Long> orderPurchaserNo, com.querydsl.core.types.Expression<Long> orderNo, com.querydsl.core.types.Expression<String> memberId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<java.time.LocalDateTime> createDate) {
        super(OrderPurchaserDto.class, new Class<?>[]{long.class, long.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, orderPurchaserNo, orderNo, memberId, name, email, phone, createDate);
    }

}

