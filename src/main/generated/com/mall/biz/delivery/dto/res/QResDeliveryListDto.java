package com.mall.biz.delivery.dto.res;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mall.biz.delivery.dto.res.QResDeliveryListDto is a Querydsl Projection type for ResDeliveryListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResDeliveryListDto extends ConstructorExpression<ResDeliveryListDto> {

    private static final long serialVersionUID = 1397759612L;

    public QResDeliveryListDto(com.querydsl.core.types.Expression<Long> deliveryNo, com.querydsl.core.types.Expression<Long> orderNo, com.querydsl.core.types.Expression<Integer> totalPrice, com.querydsl.core.types.Expression<Long> itemNo, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<com.mall.biz.delivery.entity.DeliveryCode> deliveryCode) {
        super(ResDeliveryListDto.class, new Class<?>[]{long.class, long.class, int.class, long.class, String.class, com.mall.biz.delivery.entity.DeliveryCode.class}, deliveryNo, orderNo, totalPrice, itemNo, itemName, deliveryCode);
    }

}

