package com.mall.biz.order.repository;

import com.mall.biz.order.dto.res.ResOrderItemDto;
import com.mall.biz.order.entity.Order;
import com.mall.biz.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long>, OrderStatusRepositoryCustom, QuerydslPredicateExecutor<OrderStatus> {
}
