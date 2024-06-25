package com.mall.biz.item.dto.res;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mall.biz.item.dto.res.QResItemListDto is a Querydsl Projection type for ResItemListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResItemListDto extends ConstructorExpression<ResItemListDto> {

    private static final long serialVersionUID = -1331767298L;

    public QResItemListDto(com.querydsl.core.types.Expression<Long> itemId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<com.mall.biz.item.entity.CategoryCode> categoryCode, com.querydsl.core.types.Expression<com.mall.biz.item.entity.GenderCode> genderCode) {
        super(ResItemListDto.class, new Class<?>[]{long.class, String.class, int.class, int.class, com.mall.biz.item.entity.CategoryCode.class, com.mall.biz.item.entity.GenderCode.class}, itemId, name, price, quantity, categoryCode, genderCode);
    }

}

