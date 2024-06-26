package com.mall.biz.order.repository;

import com.mall.biz.order.dto.OrderPurchaserDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPurchaserRepositoryCustom {
    OrderPurchaserDto searchOrderPurchaserDto(Long orderNo);
}
