package com.mall.biz.order.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mall.biz.order.dto.QOrderStatusDto is a Querydsl Projection type for OrderStatusDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderStatusDto extends ConstructorExpression<OrderStatusDto> {

    private static final long serialVersionUID = 2016560246L;

    public QOrderStatusDto(com.querydsl.core.types.Expression<Long> orderStatusNo, com.querydsl.core.types.Expression<java.time.LocalDateTime> createDate, com.querydsl.core.types.Expression<com.mall.biz.order.entity.OrderCode> orderCode, com.querydsl.core.types.Expression<Long> orderNo) {
        super(OrderStatusDto.class, new Class<?>[]{long.class, java.time.LocalDateTime.class, com.mall.biz.order.entity.OrderCode.class, long.class}, orderStatusNo, createDate, orderCode, orderNo);
    }

}

