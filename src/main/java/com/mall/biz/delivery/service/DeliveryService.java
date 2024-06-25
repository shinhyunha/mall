package com.mall.biz.delivery.service;

import com.mall.biz.common.repository.GroupCodeDetailRepository;
import com.mall.biz.delivery.dto.req.ReqDeliveryDto;
import com.mall.biz.delivery.entity.Delivery;
import com.mall.biz.delivery.entity.DeliveryCode;
import com.mall.biz.delivery.entity.DeliveryStatus;
import com.mall.biz.delivery.repository.DeliveryRepository;
import com.mall.biz.delivery.repository.DeliveryStatusRepository;
import com.mall.biz.order.entity.Order;
import com.mall.biz.order.repository.OrderRepository;
import com.mall.common.exception.InputCheckException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final OrderRepository orderRepository;
    private final GroupCodeDetailRepository groupCodeDetailRepository;

    @Transactional
    public void saveDelivery(ReqDeliveryDto reqDeliveryDto) {
        // 주문 번호 확인
        Order order = orderRepository.findById(reqDeliveryDto.getOrderNo()).orElseThrow(()
                -> new InputCheckException("주문번호를 확인하세요."));

        // 배송 저장
        reqDeliveryDto.changeDeliverCode(DeliveryCode.CRT);
        Delivery delivery = reqDeliveryDto.dtoToEntity(order);
        deliveryRepository.save(delivery);

        // 배송 상태 저장
        DeliveryStatus deliveryStatus = new DeliveryStatus(delivery, delivery.getDeliveryCode());
        deliveryStatusRepository.save(deliveryStatus);
    }
}
