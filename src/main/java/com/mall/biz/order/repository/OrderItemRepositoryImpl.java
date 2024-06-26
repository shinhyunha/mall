package com.mall.biz.order.repository;

import com.mall.biz.order.dto.res.QResOrderItemDto;
import com.mall.biz.order.dto.res.ResOrderItemDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.mall.biz.order.entity.QOrderItem.orderItem;

public class OrderItemRepositoryImpl implements OrderItemRepostioryCustom{
    private final JPAQueryFactory queryFactory;

    public OrderItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ResOrderItemDto> searchOrderItemList(Long orderNo) {
        return queryFactory
                .select(new QResOrderItemDto(
                        orderItem.id.as("orfderItemNo"),
                        orderItem.order.id.as("orderNo"),
                        orderItem.item.id.as("itemNo"),
                        orderItem.item.name.as("itemName"),
                        orderItem.itemPrice,
                        orderItem.orderQuantity,
                        orderItem.totalPrice,
                        orderItem.sequence
                ))
                .from(orderItem)
                .where(
                        orderItem.order.id.eq(orderNo)
                )
                .fetch();
    }
}
