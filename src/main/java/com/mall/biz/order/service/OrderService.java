package com.mall.biz.order.service;

import com.mall.biz.common.repository.GroupCodeDetailRepository;
import com.mall.biz.delivery.service.DeliveryService;
import com.mall.biz.item.entity.Item;
import com.mall.biz.item.repository.ItemRepository;
import com.mall.biz.member.entity.Member;
import com.mall.biz.member.repository.MemberRepository;
import com.mall.biz.order.dto.OrderItemDto;
import com.mall.biz.order.dto.OrderStatusDto;
import com.mall.biz.order.dto.req.ReqOrderSearchFilter;
import com.mall.biz.order.dto.req.ResOrderPurchaserDto;
import com.mall.biz.order.dto.req.ReqSaveOrderDto;
import com.mall.biz.order.dto.res.ResOrderDto;
import com.mall.biz.order.dto.res.ResOrderListDto;
import com.mall.biz.order.entity.*;
import com.mall.biz.order.repository.OrderItemRepository;
import com.mall.biz.order.repository.OrderPurchaserRepository;
import com.mall.biz.order.repository.OrderRepository;
import com.mall.biz.order.repository.OrderStatusRepository;
import com.mall.common.exception.InputCheckException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderPurchaserRepository orderPurchaserRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final DeliveryService deliveryService;

    private final GroupCodeDetailRepository groupCodeDetailRepository;

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Page<ResOrderListDto> searchOrderList(ReqOrderSearchFilter reqOrderSearchFilter, Pageable pageable) {
        // order 목록 조회
        Page<ResOrderListDto> result = orderRepository.searchOrderList(reqOrderSearchFilter, pageable);

        // 코드명 입력
        if (result != null) {
            for (ResOrderListDto item : result) {
                item.setOrderStatusName(groupCodeDetailRepository.findGroupCodeDetail("10003", String.valueOf(item.getOrderCode())));
                item.setDeliveryName(groupCodeDetailRepository.findGroupCodeDetail("10004", String.valueOf(item.getDeliveryCode())));
            }
        }

        return result;
    }

    @Transactional
    public Long saveOrder(ReqSaveOrderDto reqSaveOrderDto) {
        // order 생성
        Order order = new Order(OrderCode.CRT, reqSaveOrderDto.getTotalPrice());
        orderRepository.save(order);

        // 주문 상태 저장
        OrderStatus orderStatus = new OrderStatus(order, order.getOrderCode());
        orderStatusRepository.save(orderStatus);

        // 전체 금액 계산용
        int totalPrice = 0;
        // 주문 상품 확인
        if (reqSaveOrderDto.getOrderItemDtoList() != null) {
            List<OrderItemDto> orderItemDtoList = reqSaveOrderDto.getOrderItemDtoList();
            int count = 1;
            for (OrderItemDto orderItemDto : orderItemDtoList) {
                // 상품 아이디 확인
                Item item = itemRepository.findById(orderItemDto.getItemNo()).orElseThrow(()
                        -> new InputCheckException("상품 아이디를 확인하세요."));

                // 상품 가격확인
                if (item.checkItemPrice(orderItemDto.getItemPrice()))
                    throw new InputCheckException("상품 가격이 불일치합니다.");

                // 상품 재고 수량 확인
                if (item.checkItemQuantity(orderItemDto.getOrderQuantity()))
                    throw new InputCheckException("상품 재고가 부족합니다.");

                // 상품 재고 줄이기
                item.reduceQuantity(orderItemDto.getOrderQuantity());

                // 상품별 totalPrice 추가
                totalPrice += item.calculateTotalPrice(orderItemDto.getOrderQuantity());

                // OrderItemDto 저장
                OrderItem orderItem = orderItemDto.dtoToEntity(order, item, count++);
                orderItemRepository.save(orderItem);
            }
        }

        if (reqSaveOrderDto.getTotalPrice() != totalPrice) throw new InputCheckException("합계 금액이 일치하지 않습니다.");

        // 구매자 아이디 확인
        ResOrderPurchaserDto orderPurchaserDto = reqSaveOrderDto.getOrderPurchaserDto();
        Member member = memberRepository.findById(orderPurchaserDto.getMemberId()).orElseThrow(()
                -> new InputCheckException("회원번호를 확인하세요."));

        // order purchaser 생성
        OrderPurchaser orderPurchaser = orderPurchaserDto.dtoToEntity(order, member);
        orderPurchaserRepository.save(orderPurchaser);

        return order.getId();
    }

    @Transactional
    public void updateCompleteOrder(Long orderNo) {
        // 주문 번호 체크
        Order order = orderRepository.findById(orderNo).orElseThrow(()
                -> new InputCheckException("주문번호를 확인하세요."));

        // 주문 상태 체크
        if (!order.getOrderCode().equals(OrderCode.CRT)) throw new InputCheckException("주문 완료할 수 없는 상태입니다.");

        // 주문 완료 상태 저장
        order.changeOrderStatus(OrderCode.COM);
        OrderStatus orderStatuscomplete = new OrderStatus(order, order.getOrderCode());
        orderStatusRepository.save(orderStatuscomplete);
    }

    @Transactional
    public void updateCancelOrder(Long orderNo) {
        // 주문 번호 체크
        Order order = orderRepository.findById(orderNo).orElseThrow(()
                -> new InputCheckException("주문번호를 확인하세요."));

        // 주문 상태 체크
        if (!order.getOrderCode().equals(OrderCode.CRT)) throw new InputCheckException("주문 취소할 수 없는 상태입니다.");

        // 주문 취소 상태 저장
        order.changeOrderStatus(OrderCode.CAN);
        OrderStatus orderStatuscomplete = new OrderStatus(order, order.getOrderCode());
        orderStatusRepository.save(orderStatuscomplete);
    }

    @Transactional
    public ResOrderDto searchOrder(Long orderNo) {
        ResOrderDto result = orderRepository.searchOrder(orderNo);

        // 주문 번호 체크
        if (result == null) throw new InputCheckException("주문번호를 확인하세요.");
        
        // 주문 상태명 조회
        result.setOrderStatusName(groupCodeDetailRepository.findGroupCodeDetail("10003", String.valueOf(result.getOrderCode())));

        // 주문 상태 목록 조회
        List<OrderStatusDto> orderStatusDtoList = orderStatusRepository.searchOrderStatusList(orderNo);
        for (OrderStatusDto item : orderStatusDtoList) {
            item.setOrderStatusName(groupCodeDetailRepository.findGroupCodeDetail("10003", String.valueOf(item.getOrderCode())));
        }
        result.setOrderStatusDtoList(orderStatusDtoList);

        // 주문 배송 정보 조회
        result.setDeliveryDto(deliveryService.searchDeliveryUseOrderNo(orderNo));

        // 주문자 정보 조회
        result.setOrderPurchaserDto(orderPurchaserRepository.searchOrderPurchaserDto(orderNo));

        // 주문 상품 정보
        result.setOrderItemDtoList(orderItemRepository.searchOrderItemList(orderNo));

        return result;
    }
}
