package com.mall.biz.order.dto.res;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mall.biz.order.dto.res.QResOrderDto is a Querydsl Projection type for ResOrderDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResOrderDto extends ConstructorExpression<ResOrderDto> {

    private static final long serialVersionUID = -665876628L;

    public QResOrderDto(com.querydsl.core.types.Expression<Long> orderNo, com.querydsl.core.types.Expression<com.mall.biz.order.entity.OrderCode> orderCode, com.querydsl.core.types.Expression<Integer> totalPrice) {
        super(ResOrderDto.class, new Class<?>[]{long.class, com.mall.biz.order.entity.OrderCode.class, int.class}, orderNo, orderCode, totalPrice);
    }

}

