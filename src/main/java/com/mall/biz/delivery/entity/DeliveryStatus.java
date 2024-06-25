package com.mall.biz.delivery.entity;

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
@Table(name = "delivery_status")
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_status_no")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "delivery_no")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private DeliveryCode deliveryCode;
    private final LocalDateTime createDate = LocalDateTime.now();

    public DeliveryStatus(Long id, Delivery delivery, DeliveryCode deliveryCode) {
        this.id = id;
        this.delivery = delivery;
        this.deliveryCode = deliveryCode;
    }
}
