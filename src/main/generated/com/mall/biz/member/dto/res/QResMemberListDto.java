package com.mall.biz.member.dto.res;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.mall.biz.member.dto.res.QResMemberListDto is a Querydsl Projection type for ResMemberListDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QResMemberListDto extends ConstructorExpression<ResMemberListDto> {

    private static final long serialVersionUID = 2116208368L;

    public QResMemberListDto(com.querydsl.core.types.Expression<String> memberId, com.querydsl.core.types.Expression<String> loginId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<Integer> age, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<String> zipCode) {
        super(ResMemberListDto.class, new Class<?>[]{String.class, String.class, String.class, int.class, String.class, String.class, String.class}, memberId, loginId, name, age, phone, address, zipCode);
    }

}

