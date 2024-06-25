package com.mall.biz.order.service;

import com.mall.biz.common.repository.GroupCodeDetailRepository;
import com.mall.biz.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final GroupCodeDetailRepository groupCodeDetailRepository;
}
