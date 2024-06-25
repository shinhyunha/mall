package com.mall.biz.delivery.service;

import com.mall.biz.common.repository.GroupCodeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final GroupCodeDetailRepository groupCodeDetailRepository;
}
