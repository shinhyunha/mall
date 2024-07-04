package com.mall.biz.delivery.service;

import com.mall.biz.common.repository.GroupCodeDetailRepository;
import com.mall.biz.delivery.dto.req.ReqDeliveryDto;
import com.mall.biz.delivery.dto.req.ReqDeliverySearchFilter;
import com.mall.biz.delivery.dto.req.ReqUpdateDeliveryDto;
import com.mall.biz.delivery.dto.res.ResDeliverStatusDto;
import com.mall.biz.delivery.dto.res.ResDeliveryDto;
import com.mall.biz.delivery.dto.res.ResDeliveryListDto;
import com.mall.biz.delivery.entity.Delivery;
import com.mall.biz.delivery.entity.DeliveryCode;
import com.mall.biz.delivery.entity.DeliveryStatus;
import com.mall.biz.delivery.repository.DeliveryRepository;
import com.mall.biz.delivery.repository.DeliveryStatusRepository;
import com.mall.biz.order.dto.res.ResOrderItemDto;
import com.mall.biz.order.entity.Order;
import com.mall.biz.order.repository.OrderItemRepository;
import com.mall.biz.order.repository.OrderRepository;
import com.mall.common.exception.InputCheckException;
import com.mall.common.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryStatusRepository deliveryStatusRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final GroupCodeDetailRepository groupCodeDetailRepository;

    @Transactional(readOnly = true)
    public Page<ResDeliveryListDto> searchDeliveryList(ReqDeliverySearchFilter reqDeliverySearchFilter, Pageable pageable) {
        // 날짜 검증
        if (!(reqDeliverySearchFilter.getFromDate() == null) &&  !reqDeliverySearchFilter.getFromDate().isBlank() &&
                !(reqDeliverySearchFilter.getToDate() == null) && !reqDeliverySearchFilter.getToDate().isBlank()) {
            String fromDate = reqDeliverySearchFilter.getFromDate();
            String toDate = reqDeliverySearchFilter.getToDate();
            ValidUtils.validBetweenDate(fromDate, toDate);
            reqDeliverySearchFilter.setFromDate(fromDate + " 00:00:00");
            reqDeliverySearchFilter.setToDate(toDate + " 23:59:59");
        }

        // 배송 목록 조회
        Page<ResDeliveryListDto> results = deliveryRepository.searchDeliveryList(reqDeliverySearchFilter, pageable);

        // 배송상태명 조회
        if (results != null) {
            for (ResDeliveryListDto item : results) {
                item.setDeliveryName(groupCodeDetailRepository.findGroupCodeDetail("10004", String.valueOf(item.getDeliveryCode())));
            }
        }

        return results;
    }

    @Transactional(readOnly = true)
    public ResDeliveryDto searchDelivery(Long deliverNo) {
        // 배송 세부 조회
        ResDeliveryDto result = deliveryRepository.searchDeliveryDetail(deliverNo);

        // 배송 정보 미조회시 예외 처리
        if (result == null) throw new InputCheckException("배송번호를 확인하세요");

        // 배송상태 목록 조회
        List<ResDeliverStatusDto> resDeliverStatusDtoList = deliveryStatusRepository.searchOrderStatusList(deliverNo);
        if (resDeliverStatusDtoList != null) {
            for (ResDeliverStatusDto item : resDeliverStatusDtoList) {
                item.setDeliveryName(groupCodeDetailRepository.findGroupCodeDetail("10004", String.valueOf(item.getDeliveryCode())));
            }
        }
        result.setDeliveryStatusList(resDeliverStatusDtoList);

        // 주문 상품 조회
        List<ResOrderItemDto> resOrderItemDtoList = orderItemRepository.searchOrderItemList(result.getOrderNo());
        result.setDeliveryItemList(resOrderItemDtoList);

        // 공통코드명 조회
        result.setDeliveryName(groupCodeDetailRepository.findGroupCodeDetail("10004", String.valueOf(result.getDeliveryCode())));

        return result;
    }

    @Transactional(readOnly = true)
    public ResDeliveryDto searchDeliveryUseOrderNo(Long orderNo) {
        // 배송 세부 조회
        ResDeliveryDto result = deliveryRepository.searchDeliveryDetailUseOrderNo(orderNo);

        // 배송 정보 미조회시 예외 처리
        if (result == null) {
            return null;
        } else {
            // 공통코드명 조회
            result.setDeliveryName(groupCodeDetailRepository.findGroupCodeDetail("10004", String.valueOf(result.getDeliveryCode())));
        }

        return result;
    }

    @Transactional
    public void saveDelivery(ReqDeliveryDto reqDeliveryDto) {
        // 주문 번호 확인
        Order order = orderRepository.findById(reqDeliveryDto.getOrderNo()).orElseThrow(()
                -> new InputCheckException("주문번호를 확인하세요."));

        // 주문 완료 상태 체크
        order.checkOrderStatusComplete();

        // 배송 저장
        reqDeliveryDto.changeDeliverCode(DeliveryCode.CRT);
        Delivery delivery = reqDeliveryDto.dtoToEntity(order);
        deliveryRepository.save(delivery);

        // 배송 상태 저장
        DeliveryStatus deliveryStatus = new DeliveryStatus(delivery, delivery.getDeliveryCode());
        deliveryStatusRepository.save(deliveryStatus);
    }

    @Transactional
    public void updateOngoingDeliveryStatus(Long deliveryNo) {
        // 배송번호 체크
        Delivery delivery = vaildationDeliveryNo(deliveryNo);

        // 배송 생성 상태 체크
        if (!delivery.getDeliveryCode().equals(DeliveryCode.CRT)) throw new InputCheckException("본 배송은 배송생성 상태가 아닙니다.");

        // 배송상태 진행중 변경
        delivery.updateDeliveryStatus(DeliveryCode.ING);
        DeliveryStatus deliveryStatus = new DeliveryStatus(delivery, DeliveryCode.ING);
        deliveryStatusRepository.save(deliveryStatus);
    }

    @Transactional
    public void updateCompleteDeliveryStatus(Long deliveryNo) {
        // 배송번호 체크
        Delivery delivery = vaildationDeliveryNo(deliveryNo);

        // 배송 생성 상태 체크
        switch (delivery.getDeliveryCode()) {
            case DeliveryCode.COM, DeliveryCode.CRT, DeliveryCode.CAN -> throw new InputCheckException("본 배송은 배송중이 아닙니다.");
        }

        // 배송상태 진행중 변경
        delivery.updateDeliveryStatus(DeliveryCode.COM);
        DeliveryStatus deliveryStatus = new DeliveryStatus(delivery, DeliveryCode.COM);
        deliveryStatusRepository.save(deliveryStatus);
    }

    @Transactional
    public void updateCancelDeliveryStatus(Long deliveryNo) {
        // 배송번호 체크
        Delivery delivery = vaildationDeliveryNo(deliveryNo);

        // 배송 생성 상태 체크
        switch (delivery.getDeliveryCode()) {
            case DeliveryCode.COM, DeliveryCode.CAN -> throw new InputCheckException("본 배송은 취소할 수 없습니다.");
        }

        // 배송상태 진행중 변경
        delivery.updateDeliveryStatus(DeliveryCode.CAN);
        DeliveryStatus deliveryStatus = new DeliveryStatus(delivery, DeliveryCode.CAN);
        deliveryStatusRepository.save(deliveryStatus);
    }

    @Transactional(readOnly = true)
    public Delivery vaildationDeliveryNo(Long deliveryNo) {
        // 배송번호 체크
        Delivery delivery = deliveryRepository.findById(deliveryNo).orElseThrow(()
                -> new InputCheckException("배송번호를 확인하세요."));
        return delivery;
    }

    @Transactional
    public void updateDeliveryInformation(ReqUpdateDeliveryDto reqUpdateDeliveryDto) {
        Delivery delivery = deliveryRepository.findById(reqUpdateDeliveryDto.getDeliveryNo()).orElseThrow(()
                -> new InputCheckException("배송번호를 확인하세요."));

        switch (delivery.getDeliveryCode()) {
            case DeliveryCode.ING, DeliveryCode.COM, DeliveryCode.CAN -> throw new InputCheckException("배송 기본 정보를 수정할 수 없는 배송상태입니다.");
        }

        // 배송 기본 정보 수정
        delivery.updateDeliveryInformation(reqUpdateDeliveryDto);
    }
}
