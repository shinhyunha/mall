package com.mall.biz.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_no")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_no")
    private Order order;

    @Enumerated(EnumType.STRING)
    private OrderCode orderCode;

    private final LocalDateTime createDate = LocalDateTime.now();

    public OrderStatus(Long id, Order order, OrderCode orderCode) {
        this.id = id;
        this.order = order;
        this.orderCode = orderCode;
    }
}