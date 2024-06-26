package com.mall.biz.order.entity;

import com.mall.common.entity.BaseEntity;
import com.mall.common.exception.InputCheckException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderCode orderCode;

    private int totalPrice;

    public Order(OrderCode orderCode, int totalPrice) {
        this.orderCode = orderCode;
        this.totalPrice = totalPrice;
    }

    public void changeOrderStatus(OrderCode orderCode) {
        this.orderCode = orderCode;
        this.updateBaseEntity();
    }

    public void checkOrderStatusComplete() {
        if (!this.orderCode.equals(OrderCode.COM)) throw new InputCheckException("주문완료 상태가 아닙니다.");
    }
}
