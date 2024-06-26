package com.mall.biz.delivery.dto.res;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mall.biz.delivery.dto.res.QResDeliverStatusDto is a Querydsl Projection type for ResDeliverStatusDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResDeliverStatusDto extends ConstructorExpression<ResDeliverStatusDto> {

    private static final long serialVersionUID = -1436550631L;

    public QResDeliverStatusDto(com.querydsl.core.types.Expression<Long> deliveryStatusNo, com.querydsl.core.types.Expression<Long> deliveryNo, com.querydsl.core.types.Expression<com.mall.biz.delivery.entity.DeliveryCode> deliveryCode, com.querydsl.core.types.Expression<java.time.LocalDateTime> createDate) {
        super(ResDeliverStatusDto.class, new Class<?>[]{long.class, long.class, com.mall.biz.delivery.entity.DeliveryCode.class, java.time.LocalDateTime.class}, deliveryStatusNo, deliveryNo, deliveryCode, createDate);
    }

}

