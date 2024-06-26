package com.mall.biz.order.controller;

import com.mall.biz.item.dto.res.ResItemDto;
import com.mall.biz.order.dto.res.ResOrderDto;
import com.mall.biz.order.service.OrderService;
import com.mall.biz.order.dto.req.ReqSaveOrderDto;
import com.mall.common.model.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(name="주문", description = "주문 API")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{orderNo}")
    @Operation(summary = "주문 단건 조회", description = "주문 단건을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 단건 조회 성공", content = @Content(schema = @Schema(implementation = ResOrderDto.class)))
    })
    public SuccessResponse searchOrder(@PathVariable(name = "orderNo") Long orderNo) {
        ResOrderDto result = orderService.searchOrder(orderNo);
        return new SuccessResponse(result);
    }

    @PostMapping("")
    @Operation(summary = "주문 등록", description = "주문을 등록한다.")
    public SuccessResponse saveOrder(@RequestBody @Valid ReqSaveOrderDto reqSaveOrderDto) {
        Long orderNo = orderService.saveOrder(reqSaveOrderDto);
        return new SuccessResponse(orderNo);
    }

    @PutMapping("/complete/{orderNo}")
    @Operation(summary = "주문 완료", description = "주문 완료상태를 등록한다.")
    public SuccessResponse updateCompleteOrder(@PathVariable("orderNo") Long orderNo) {
        orderService.updateCompleteOrder(orderNo);
        return new SuccessResponse();
    }

    @PutMapping("/cancel/{orderNo}")
    @Operation(summary = "주문 취소", description = "주문 취소상태를 등록한다.")
    public SuccessResponse updateCancelOrder(@PathVariable("orderNo") Long orderNo) {
        orderService.updateCancelOrder(orderNo);
        return new SuccessResponse();
    }
}