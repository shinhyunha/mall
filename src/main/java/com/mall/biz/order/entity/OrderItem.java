package com.mall.biz.order.entity;

import com.mall.biz.item.entity.Item;
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
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_no")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_no")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_no")
    private Item item;

    private int itemPrice;
    private int orderQuantity;
    private int totalPrice;
    private int sequence;
    private final LocalDateTime createDate = LocalDateTime.now();

    public OrderItem(Order order, Item item, int itemPrice, int orderQuantity, int sequence) {
        this.order = order;
        this.item = item;
        this.itemPrice = itemPrice;
        this.orderQuantity = orderQuantity;
        this.totalPrice = (itemPrice * orderQuantity);
        this.sequence = sequence;
    }
}
