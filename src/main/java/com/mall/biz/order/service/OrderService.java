package com.mall.biz.order.service;

import com.mall.biz.common.repository.GroupCodeDetailRepository;
import com.mall.biz.item.entity.Item;
import com.mall.biz.item.repository.ItemRepository;
import com.mall.biz.item.service.ItemService;
import com.mall.biz.member.entity.Member;
import com.mall.biz.member.repository.MemberRepository;
import com.mall.biz.order.dto.OrderItemDto;
import com.mall.biz.order.dto.req.OrderPurchaserDto;
import com.mall.biz.order.dto.req.ReqSaveOrderDto;
import com.mall.biz.order.entity.*;
import com.mall.biz.order.repository.OrderItemRepository;
import com.mall.biz.order.repository.OrderPurchaserRepository;
import com.mall.biz.order.repository.OrderRepository;
import com.mall.biz.order.repository.OrderStatusRepository;
import com.mall.common.exception.InputCheckException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderPurchaserRepository orderPurchaserRepository;
    private final OrderStatusRepository orderStatusRepository;

    private final GroupCodeDetailRepository groupCodeDetailRepository;

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

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
                OrderItem orderItem = orderItemDto.dtoToEntity(order, item);
                orderItemRepository.save(orderItem);
            }
        }

        if (reqSaveOrderDto.getTotalPrice() != totalPrice) throw new InputCheckException("합계 금액이 일치하지 않습니다.");

        // 구매자 아이디 확인
        OrderPurchaserDto orderPurchaserDto = reqSaveOrderDto.getOrderPurchaserDto();
        Member member = memberRepository.findById(orderPurchaserDto.getMemberId()).orElseThrow(()
                -> new InputCheckException("회원번호를 확인하세요."));

        // order purchaser 생성
        OrderPurchaser orderPurchaser = orderPurchaserDto.dtoToEntity(order, member);
        orderPurchaserRepository.save(orderPurchaser);

        // 주문 완료 상태 저장
        order.changeOrderStatus(OrderCode.COM);
        OrderStatus orderStatuscomplete = new OrderStatus(order, order.getOrderCode());
        orderStatusRepository.save(orderStatuscomplete);

        return order.getId();

    }
}
