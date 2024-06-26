package com.mall.biz.order.repository;

import com.mall.biz.order.dto.req.ReqOrderSearchFilter;
import com.mall.biz.order.dto.res.ResOrderDto;
import com.mall.biz.order.dto.res.ResOrderListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryCustom {
    ResOrderDto searchOrder(Long orderNo);

    Page<ResOrderListDto> searchOrderList(ReqOrderSearchFilter reqOrderSearchFilter, Pageable pageable);
}
