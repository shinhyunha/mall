package com.mall.biz.delivery.controller;

import com.mall.biz.delivery.service.DeliveryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
@Tag(name="배송", description = "배송 API")
public class DeliveryController {
    private final DeliveryService deliveryService;
}
