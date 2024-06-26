package com.mall.biz.order.repository;

import com.mall.biz.order.dto.res.ResOrderDto;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryCustom {
    ResOrderDto searchOrder(Long orderNo);
}
