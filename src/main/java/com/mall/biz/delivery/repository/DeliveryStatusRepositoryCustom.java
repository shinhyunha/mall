package com.mall.biz.delivery.repository;

import com.mall.biz.delivery.dto.res.ResDeliverStatusDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryStatusRepositoryCustom {
    List<ResDeliverStatusDto> searchOrderStatusList(Long deliveryNo);
}
