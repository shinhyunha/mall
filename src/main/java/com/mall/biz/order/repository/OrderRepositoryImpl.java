package com.mall.biz.order.repository;

import com.mall.biz.delivery.entity.DeliveryCode;
import com.mall.biz.delivery.entity.QDelivery;
import com.mall.biz.item.entity.QItem;
import com.mall.biz.order.dto.req.ReqOrderSearchFilter;
import com.mall.biz.order.dto.res.QResOrderDto;
import com.mall.biz.order.dto.res.QResOrderListDto;
import com.mall.biz.order.dto.res.ResOrderDto;
import com.mall.biz.order.dto.res.ResOrderListDto;
import com.mall.biz.order.entity.*;
import com.mall.common.exception.InputCheckException;
import com.querydsl.core.types.Predicate;
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
import static com.mall.biz.order.entity.QOrderPurchaser.*;
import static org.springframework.util.StringUtils.hasText;

public class OrderRepositoryImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ResOrderListDto> searchOrderList(ReqOrderSearchFilter reqOrderSearchFilter, Pageable pageable) {
        List<ResOrderListDto> content = queryFactory
                .select(new QResOrderListDto(
                        order.id.as("orderNo"),
                        order.orderCode,
                        order.totalPrice,
                        item.id.as("itemNo"),
                        item.name.as("itemName"),
                        orderPurchaser.name,
                        orderPurchaser.id.as("orderPurchaserNo"),
                        delivery.deliveryCode,
                        order.createDate
                ))
                .from(order)
                .join(orderItem).on(orderItem.order.eq(order))
                .join(orderItem.item, item)
                .join(orderPurchaser).on(orderPurchaser.order.eq(order))
                .leftJoin(delivery).on(delivery.order.eq(order))
                .where(
                        orderNoEq(reqOrderSearchFilter),
                        itemNameLike(reqOrderSearchFilter),
                        purchaserNameLike(reqOrderSearchFilter),
                        orderCodeEq(reqOrderSearchFilter),
                        betweenDate(reqOrderSearchFilter),
                        orderItem.sequence.eq(1)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(order.id.desc())
                .fetch();

        JPAQuery<Order> countQuery = queryFactory
                .select(order)
                .from(order)
                .join(orderItem).on(orderItem.order.eq(order))
                .join(orderItem.item, item)
                .join(orderPurchaser).on(orderPurchaser.order.eq(order))
                .leftJoin(delivery).on(delivery.order.eq(order))
                .where(
                        orderNoEq(reqOrderSearchFilter),
                        itemNameLike(reqOrderSearchFilter),
                        purchaserNameLike(reqOrderSearchFilter),
                        orderCodeEq(reqOrderSearchFilter),
                        betweenDate(reqOrderSearchFilter),
                        orderItem.sequence.eq(1)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
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

    private BooleanExpression orderNoEq(ReqOrderSearchFilter reqOrderSearchFilter) {
        if (reqOrderSearchFilter.getSearchCd() != null
                && reqOrderSearchFilter.getSearchCd().equals("10")) {
            try {
                return hasText(reqOrderSearchFilter.getSearchKeyword()) ? order.id.eq(Long.valueOf(reqOrderSearchFilter.getSearchKeyword())) : null;
            } catch (NumberFormatException e) {
                throw new InputCheckException("주문번호는 문자 혹은 특수기호를 입력할 수 없습니다.");
            }
        }
        return null;
    }

    private BooleanExpression itemNameLike(ReqOrderSearchFilter reqOrderSearchFilter) {
        if (reqOrderSearchFilter.getSearchCd() != null
                && reqOrderSearchFilter.getSearchCd().equals("20")) {
            return hasText(reqOrderSearchFilter.getSearchKeyword()) ? item.name.like('%' + reqOrderSearchFilter.getSearchKeyword() + '%') : null;
        }
        return null;
    }

    private BooleanExpression purchaserNameLike(ReqOrderSearchFilter reqOrderSearchFilter) {
        if (reqOrderSearchFilter.getSearchCd() != null
                && reqOrderSearchFilter.getSearchCd().equals("30")) {
            return hasText(reqOrderSearchFilter.getSearchKeyword()) ? orderPurchaser.name.like('%' + reqOrderSearchFilter.getSearchKeyword() + '%') : null;
        }
        return null;
    }

    private BooleanExpression orderCodeEq(ReqOrderSearchFilter reqOrderSearchFilter) {
        if (reqOrderSearchFilter.getOrderCode() != null) {
            return switch (reqOrderSearchFilter.getOrderCode()) {
                case OrderCode.CRT -> order.orderCode.eq(OrderCode.CRT);
                case OrderCode.COM -> order.orderCode.eq(OrderCode.COM);
                case OrderCode.CAN -> order.orderCode.eq(OrderCode.CAN);
            };
        }
        return null;
    }

    private BooleanExpression betweenDate(ReqOrderSearchFilter reqOrderSearchFilter) {
        if (!(reqOrderSearchFilter.getFromDate() == null) && !reqOrderSearchFilter.getFromDate().isBlank() &&
                !(reqOrderSearchFilter.getToDate() == null) && !reqOrderSearchFilter.getToDate().isBlank()) {
            String tempFromDate = reqOrderSearchFilter.getFromDate().replace(" ", "T");
            LocalDateTime fromDate = LocalDateTime.parse(tempFromDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String tempToDate = reqOrderSearchFilter.getToDate().replace(" ", "T");
            LocalDateTime toDate = LocalDateTime.parse(tempToDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return order.createDate.between(fromDate, toDate);
        }
        return null;
    }
}
