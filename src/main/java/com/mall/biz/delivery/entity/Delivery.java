package com.mall.biz.delivery.entity;

import com.mall.common.entity.BaseDateEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "delivery")
public class Delivery extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_no")
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryCode deliveryCode;

    private String receiverName;
    private String deliveryAddress;
    private String zipCode;
    private String phone;

    public Delivery(Long id, DeliveryCode deliveryCode, String receiverName, String deliveryAddress, String zipCode, String phone) {
        this.id = id;
        this.deliveryCode = deliveryCode;
        this.receiverName = receiverName;
        this.deliveryAddress = deliveryAddress;
        this.zipCode = zipCode;
        this.phone = phone.replace("-", "");
    }
}