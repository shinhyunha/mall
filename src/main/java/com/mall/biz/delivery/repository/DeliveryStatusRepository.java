package com.mall.biz.delivery.repository;

import com.mall.biz.delivery.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Long>, DeliveryRepositoryCustom, QuerydslPredicateExecutor<DeliveryStatus> {
}
