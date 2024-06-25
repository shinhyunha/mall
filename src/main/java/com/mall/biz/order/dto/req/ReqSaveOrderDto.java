package com.mall.biz.order.dto.req;

import com.mall.biz.order.dto.OrderItemDto;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class ReqSaveOrderDto {
    private  int totalPrice;
    private OrderPurchaserDto orderPurchaserDto;
    private List<OrderItemDto> orderItemDtoList;
}
