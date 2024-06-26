package com.mall.biz.order.repository;

import com.mall.biz.order.dto.OrderPurchaserDto;
import com.mall.biz.order.dto.QOrderPurchaserDto;
import com.mall.biz.order.entity.QOrderPurchaser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.mall.biz.order.entity.QOrderPurchaser.*;

public class OrderPurchaserRepositoryImpl implements OrderPurchaserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public OrderPurchaserRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public OrderPurchaserDto searchOrderPurchaserDto(Long orderNo) {
        return queryFactory
                .select(new QOrderPurchaserDto(
                        orderPurchaser.id.as("orderPurchaserNo"),
                        orderPurchaser.order.id.as("orderNo"),
                        orderPurchaser.member.id.as("memberId"),
                        orderPurchaser.name,
                        orderPurchaser.email,
                        orderPurchaser.phone,
                        orderPurchaser.createDate
                ))
                .from(orderPurchaser)
                .where(orderPurchaser.order.id.eq(orderNo))
                .fetchOne();
    }
}
