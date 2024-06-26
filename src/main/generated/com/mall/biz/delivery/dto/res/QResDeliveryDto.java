package com.mall.biz.delivery.dto.res;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mall.biz.delivery.dto.res.QResDeliveryDto is a Querydsl Projection type for ResDeliveryDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResDeliveryDto extends ConstructorExpression<ResDeliveryDto> {

    private static final long serialVersionUID = 969062778L;

    public QResDeliveryDto(com.querydsl.core.types.Expression<Long> deliveryNo, com.querydsl.core.types.Expression<Long> orderNo, com.querydsl.core.types.Expression<Integer> totalPrice, com.querydsl.core.types.Expression<com.mall.biz.delivery.entity.DeliveryCode> deliveryCode, com.querydsl.core.types.Expression<String> receiverName, com.querydsl.core.types.Expression<String> deliveryAddress, com.querydsl.core.types.Expression<String> zipCode, com.querydsl.core.types.Expression<String> phone) {
        super(ResDeliveryDto.class, new Class<?>[]{long.class, long.class, int.class, com.mall.biz.delivery.entity.DeliveryCode.class, String.class, String.class, String.class, String.class}, deliveryNo, orderNo, totalPrice, deliveryCode, receiverName, deliveryAddress, zipCode, phone);
    }

}

