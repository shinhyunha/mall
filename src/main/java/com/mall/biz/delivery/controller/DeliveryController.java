package com.mall.biz.delivery.controller;

import com.mall.biz.delivery.dto.req.ReqDeliveryDto;
import com.mall.biz.delivery.service.DeliveryService;
import com.mall.biz.order.dto.req.ReqSaveOrderDto;
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
@RequestMapping("/api/delivery")
@Tag(name="배송", description = "배송 API")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("")
    @Operation(summary = "배송 등록", description = "배송을 등록한다.")
    public SuccessResponse saveOrder(@RequestBody @Valid ReqDeliveryDto reqDeliveryDto) {
        deliveryService.saveDelivery(reqDeliveryDto);
        return new SuccessResponse();
    }
}
