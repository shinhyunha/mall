package com.mall.biz.delivery.repository;

import com.mall.biz.delivery.dto.res.QResDeliverStatusDto;
import com.mall.biz.delivery.dto.res.ResDeliverStatusDto;
import com.mall.biz.delivery.entity.QDeliveryStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.mall.biz.delivery.entity.QDeliveryStatus.*;

public class DeliveryStatusRepositoryImpl implements DeliveryStatusRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public DeliveryStatusRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ResDeliverStatusDto> searchOrderStatusList(Long deliveryNo) {
        return queryFactory
                .select(new QResDeliverStatusDto(
                        deliveryStatus.id.as("deliveryStatusNo"),
                        deliveryStatus.delivery.id.as("deliveryNo"),
                        deliveryStatus.deliveryCode,
                        deliveryStatus.createDate
                ))
                .from(deliveryStatus)
                .where(deliveryStatus.delivery.id.eq(deliveryNo))
                .orderBy(deliveryStatus.id.desc())
                .fetch();
    }
}
