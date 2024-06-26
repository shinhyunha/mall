package com.mall.biz.delivery.repository;

import com.mall.biz.delivery.dto.req.ReqDeliverySearchFilter;
import com.mall.biz.delivery.dto.res.ResDeliveryDto;
import com.mall.biz.delivery.dto.res.ResDeliveryListDto;
import com.mall.biz.delivery.entity.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepositoryCustom {
    ResDeliveryDto searchDeliveryDetail(Long deliveryNo);

    Page<ResDeliveryListDto> searchDeliveryList(ReqDeliverySearchFilter reqDeliverySearchFilter, Pageable pageable);
}
