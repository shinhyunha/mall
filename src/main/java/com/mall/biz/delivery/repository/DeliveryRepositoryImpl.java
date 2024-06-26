package com.mall.biz.delivery.repository;

import com.mall.biz.delivery.dto.req.ReqDeliverySearchFilter;
import com.mall.biz.delivery.dto.res.QResDeliveryDto;
import com.mall.biz.delivery.dto.res.QResDeliveryListDto;
import com.mall.biz.delivery.dto.res.ResDeliveryDto;
import com.mall.biz.delivery.dto.res.ResDeliveryListDto;
import com.mall.biz.delivery.entity.Delivery;
import com.mall.biz.delivery.entity.DeliveryCode;
import com.mall.common.exception.InputCheckException;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.mall.biz.delivery.entity.QDelivery.*;
import static com.mall.biz.item.entity.QItem.*;
import static com.mall.biz.order.entity.QOrder.*;
import static com.mall.biz.order.entity.QOrderItem.*;
import static org.springframework.util.StringUtils.hasText;

public class DeliveryRepositoryImpl implements DeliveryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public DeliveryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public ResDeliveryDto searchDeliveryDetail(Long deliveryNo) {
        return queryFactory
                .select(new QResDeliveryDto(
                        delivery.id.as("deliveryNo"),
                        order.id.as("orderNo"),
                        order.totalPrice,
                        delivery.deliveryCode,
                        delivery.receiverName,
                        delivery.deliveryAddress,
                        delivery.zipCode,
                        delivery.phone
                ))
                .from(delivery)
                .join(delivery.order, order)
                .join(orderItem).on(orderItem.order.eq(order))
                .join(orderItem.item, item)
                .where(
                        delivery.id.eq(deliveryNo),
                        orderItem.sequence.eq(1)
                )
                .fetchOne();
    }

    @Override
    public Page<ResDeliveryListDto> searchDeliveryList(ReqDeliverySearchFilter reqDeliverySearchFilter, Pageable pageable) {
        List<ResDeliveryListDto> content = queryFactory
                .select(new QResDeliveryListDto(
                        delivery.id.as("deliveryNo"),
                        order.id.as("orderNo"),
                        order.totalPrice,
                        orderItem.id.as("itemNo"),
                        item.name.as("itemName"),
                        delivery.deliveryCode
                ))
                .from(delivery)
                .join(delivery.order, order)
                .join(orderItem).on(orderItem.order.eq(order))
                .join(orderItem.item, item)
                .where(
                        deliveryNoEq(reqDeliverySearchFilter),
                        itemNameEq(reqDeliverySearchFilter),
                        deliveryCodeEq(reqDeliverySearchFilter),
                        betweenDate(reqDeliverySearchFilter),
                        orderItem.sequence.eq(1)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(delivery.id.desc())
                .fetch();

        JPAQuery<Delivery> countQuery = queryFactory
                .select(delivery)
                .from(delivery)
                .join(delivery.order, order)
                .join(orderItem).on(orderItem.order.eq(order))
                .join(orderItem.item, item)
                .where(
                        deliveryNoEq(reqDeliverySearchFilter),
                        itemNameEq(reqDeliverySearchFilter),
                        deliveryCodeEq(reqDeliverySearchFilter),
                        betweenDate(reqDeliverySearchFilter),
                        orderItem.sequence.eq(1)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    @Override
    public ResDeliveryDto searchDeliveryDetailUseOrderNo(Long orderNo) {
        return queryFactory
                .select(new QResDeliveryDto(
                        delivery.id.as("deliveryNo"),
                        order.id.as("orderNo"),
                        order.totalPrice,
                        delivery.deliveryCode,
                        delivery.receiverName,
                        delivery.deliveryAddress,
                        delivery.zipCode,
                        delivery.phone
                ))
                .from(delivery)
                .join(delivery.order, order)
                .join(orderItem).on(orderItem.order.eq(order))
                .join(orderItem.item, item)
                .where(
                        delivery.order.id.eq(orderNo),
                        orderItem.sequence.eq(1)
                )
                .fetchOne();    }

    private BooleanExpression deliveryNoEq(ReqDeliverySearchFilter reqDeliverySearchFilter) {
        if (reqDeliverySearchFilter.getSearchCd() != null && reqDeliverySearchFilter.getSearchCd().equals("10")) {
            try {
                return hasText(reqDeliverySearchFilter.getSearchKeyword()) ? delivery.id.eq(Long.valueOf(reqDeliverySearchFilter.getSearchKeyword())) : null;
            } catch (NumberFormatException e) {
                throw new InputCheckException("배송번호는 문자 혹은 특수기호를 입력할 수 없습니다.");
            }
        }
        return null;
    }

    private BooleanExpression itemNameEq(ReqDeliverySearchFilter reqDeliverySearchFilter) {
        if (reqDeliverySearchFilter.getSearchCd() != null
                && reqDeliverySearchFilter.getSearchCd().equals("20")) {
            return hasText(reqDeliverySearchFilter.getSearchKeyword()) ? item.name.like('%' + reqDeliverySearchFilter.getSearchKeyword() + '%') : null;
        }
        return null;
    }

    private BooleanExpression deliveryCodeEq(ReqDeliverySearchFilter reqDeliverySearchFilter) {
        if (reqDeliverySearchFilter.getDeliveryCode() != null) {
            return switch (reqDeliverySearchFilter.getDeliveryCode()) {
                case DeliveryCode.CRT -> delivery.deliveryCode.eq(DeliveryCode.CRT);
                case DeliveryCode.ING -> delivery.deliveryCode.eq(DeliveryCode.ING);
                case DeliveryCode.COM -> delivery.deliveryCode.eq(DeliveryCode.COM);
                case DeliveryCode.CAN -> delivery.deliveryCode.eq(DeliveryCode.CAN);
            };
        }
        return null;
    }

    private BooleanExpression betweenDate(ReqDeliverySearchFilter reqDeliverySearchFilter) {
        if (!(reqDeliverySearchFilter.getFromDate() == null) && !reqDeliverySearchFilter.getFromDate().isBlank() &&
                !(reqDeliverySearchFilter.getToDate() == null) && !reqDeliverySearchFilter.getToDate().isBlank()) {
            String tempFromDate = reqDeliverySearchFilter.getFromDate().replace(" ", "T");
            LocalDateTime fromDate = LocalDateTime.parse(tempFromDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String tempToDate = reqDeliverySearchFilter.getToDate().replace(" ", "T");
            LocalDateTime toDate = LocalDateTime.parse(tempToDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return delivery.createDate.between(fromDate, toDate);
        }
        return null;
    }
}
