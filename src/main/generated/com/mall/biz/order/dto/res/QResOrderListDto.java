package com.mall.biz.order.dto.res;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mall.biz.order.dto.res.QResOrderListDto is a Querydsl Projection type for ResOrderListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResOrderListDto extends ConstructorExpression<ResOrderListDto> {

    private static final long serialVersionUID = 570467182L;

    public QResOrderListDto(com.querydsl.core.types.Expression<Long> orderNo, com.querydsl.core.types.Expression<com.mall.biz.order.entity.OrderCode> orderCode, com.querydsl.core.types.Expression<Integer> totalPrice, com.querydsl.core.types.Expression<Long> itemNo, com.querydsl.core.types.Expression<String> itemName, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Long> orderPurchaserNo, com.querydsl.core.types.Expression<com.mall.biz.delivery.entity.DeliveryCode> deliveryCode, com.querydsl.core.types.Expression<java.time.LocalDateTime> createDate) {
        super(ResOrderListDto.class, new Class<?>[]{long.class, com.mall.biz.order.entity.OrderCode.class, int.class, long.class, String.class, String.class, long.class, com.mall.biz.delivery.entity.DeliveryCode.class, java.time.LocalDateTime.class}, orderNo, orderCode, totalPrice, itemNo, itemName, name, orderPurchaserNo, deliveryCode, createDate);
    }

}

