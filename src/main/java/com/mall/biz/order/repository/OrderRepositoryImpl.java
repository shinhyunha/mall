package com.mall.biz.order.repository;

import com.mall.biz.order.dto.res.QResOrderDto;
import com.mall.biz.order.dto.res.ResOrderDto;
import com.mall.biz.order.entity.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.mall.biz.order.entity.QOrder.*;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public ResOrderDto searchOrder(Long orderNo) {
        return queryFactory
                .select(new QResOrderDto(
                        order.id.as("orderNo"),
                        order.orderCode,
                        order.totalPrice
                ))
                .from(order)
                .where(
                        order.id.eq(orderNo)
                )
                .fetchOne();
    }
}
