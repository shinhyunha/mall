package com.mall.biz.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.biz.item.entity.Item;
import com.mall.biz.order.entity.Order;
import com.mall.biz.order.entity.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderItemDto {
    @Schema(description = "주문 번호", type = "Long")
    private Long orderNo;

    @Schema(description = "상품 번호", type = "Long")
    private Long itemNo;

    @Schema(description = "상품 가격", type = "int")
    private int itemPrice;

    @Schema(description = "상품 주문 수량", type = "int")
    private int orderQuantity;

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public OrderItem dtoToEntity(Order order, Item item) {
        return new OrderItem(
                order,
                item,
                this.getItemPrice(),
                this.getOrderQuantity());
    }
}
