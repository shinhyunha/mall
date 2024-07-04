package com.mall.biz.order.entity.redis;

import com.mall.biz.order.dto.OrderItemDto;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@RedisHash(value = "orders", timeToLive = 3000)
public class OrderRedis {
    @Id
    private String id;

    private String orderStatus;

    private int totalPrice;

    private String memberId;

    private String name;

    private String email;

    private String phone;

    private List<OrderItemDto> itemList;

    public OrderRedis(String id, String orderStatus, int totalPrice, String memberId, String name, String email, String phone, List<OrderItemDto> itemList) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.itemList = itemList;
    }
}
