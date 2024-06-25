package com.mall.biz.order.entity;

import com.mall.biz.item.entity.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
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
    private final int totalPrice = (itemPrice * orderQuantity);

    public OrderItem(Long id, Order order, Item item, int itemPrice, int orderQuantity) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.itemPrice = itemPrice;
        this.orderQuantity = orderQuantity;
    }
}
