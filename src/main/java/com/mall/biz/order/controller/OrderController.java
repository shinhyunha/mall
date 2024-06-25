package com.mall.biz.order.controller;

import com.mall.biz.item.dto.req.ReqSaveItemDto;
import com.mall.biz.order.service.OrderService;
import com.mall.common.model.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(name="주문", description = "주문 API")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    @Operation(summary = "주문 등록", description = "주문을 등록한다.")
    public SuccessResponse saveItem(@RequestBody @Valid ReqSaveItemDto reqSaveItemDto) {
//        itemService.saveItem(reqSaveItemDto);
        return new SuccessResponse();
    }
}
