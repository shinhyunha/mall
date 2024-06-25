package com.mall.biz.order.repository;

import com.mall.biz.order.entity.OrderPurchaser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPurchaserRepository extends JpaRepository<OrderPurchaser, Long>, OrderRepositoryCustom, QuerydslPredicateExecutor<OrderPurchaser> {
}
