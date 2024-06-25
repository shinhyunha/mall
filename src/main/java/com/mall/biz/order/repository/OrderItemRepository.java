package com.mall.biz.order.repository;

import com.mall.biz.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, OrderRepositoryCustom, QuerydslPredicateExecutor<OrderItem> {
}
