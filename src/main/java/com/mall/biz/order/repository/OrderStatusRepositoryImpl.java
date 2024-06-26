package com.mall.biz.order.repository;

import com.mall.biz.delivery.dto.res.QResDeliverStatusDto;
import com.mall.biz.delivery.dto.res.ResDeliverStatusDto;
import com.mall.biz.delivery.entity.QDeliveryStatus;
import com.mall.biz.order.dto.OrderStatusDto;
import com.mall.biz.order.dto.QOrderStatusDto;
import com.mall.biz.order.dto.res.QResOrderItemDto;
import com.mall.biz.order.dto.res.ResOrderItemDto;
import com.mall.biz.order.entity.QOrderItem;
import com.mall.biz.order.entity.QOrderStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.mall.biz.delivery.entity.QDeliveryStatus.*;
import static com.mall.biz.order.entity.QOrderItem.*;
import static com.mall.biz.order.entity.QOrderStatus.*;

public class OrderStatusRepositoryImpl implements OrderStatusRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public OrderStatusRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<OrderStatusDto> searchOrderStatusList(Long orderNo) {
        return queryFactory
                .select(new QOrderStatusDto(
                        orderStatus.id.as("orderStatusNo"),
                        orderStatus.createDate,
                        orderStatus.orderCode,
                        orderStatus.order.id.as("orderNo")
                ))
                .from(orderStatus)
                .where(orderStatus.order.id.eq(orderNo))
                .orderBy(orderStatus.id.desc())
                .fetch();
    }
}
