package com.mall.biz.order.entity;

import com.mall.biz.member.entity.Member;
import com.mall.common.entity.BaseDateEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_purchaser")
public class OrderPurchaser extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_purchaser_no")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "order_no")
    private Order order;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String name;
    private String email;
    private String phone;

    public OrderPurchaser(Order order, Member member, String name, String email, String phone) {
        this.order = order;
        this.member = member;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
