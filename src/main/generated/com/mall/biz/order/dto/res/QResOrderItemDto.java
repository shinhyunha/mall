package com.mall.biz.order.dto.res;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mall.biz.order.dto.res.QResOrderItemDto is a Querydsl Projection type for ResOrderItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResOrderItemDto extends ConstructorExpression<ResOrderItemDto> {

    private static final long serialVersionUID = -1790261031L;

    public QResOrderItemDto(com.querydsl.core.types.Expression<Long> orderItemNo, com.querydsl.core.types.Expression<Long> orderNo, com.querydsl.core.types.Expression<Long> itemNo, com.querydsl.core.types.Expression<Integer> itemPrice, com.querydsl.core.types.Expression<Integer> orderQuantity, com.querydsl.core.types.Expression<Integer> totalPrice, com.querydsl.core.types.Expression<Integer> sequence) {
        super(ResOrderItemDto.class, new Class<?>[]{long.class, long.class, long.class, int.class, int.class, int.class, int.class}, orderItemNo, orderNo, itemNo, itemPrice, orderQuantity, totalPrice, sequence);
    }

}

