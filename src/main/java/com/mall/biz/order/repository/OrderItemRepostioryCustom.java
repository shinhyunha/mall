package com.mall.biz.order.repository;

import com.mall.biz.order.dto.res.ResOrderItemDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepostioryCustom {
    List<ResOrderItemDto> searchOrderItemList(Long orderNo);
}
