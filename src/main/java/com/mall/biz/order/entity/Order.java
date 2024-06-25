package com.mall.biz.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderCode orderCode;
    private int totalPrice;

    public Order(Long id, OrderCode orderCode, int totalPrice) {
        this.id = id;
        this.orderCode = orderCode;
        this.totalPrice = totalPrice;
    }
}
