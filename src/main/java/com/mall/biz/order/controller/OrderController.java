package com.mall.biz.order.controller;

import com.mall.biz.order.dto.req.ReqOrderSearchFilter;
import com.mall.biz.order.dto.res.ResOrderDto;
import com.mall.biz.order.dto.res.ResOrderListDto;
import com.mall.biz.order.service.OrderService;
import com.mall.biz.order.dto.req.ReqSaveOrderDto;
import com.mall.common.model.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(name="주문", description = "주문 API")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    @Parameters({
            @Parameter(name = "fromDate", description = "검색시작일 (YYYY-MM-DD)", schema = @Schema(type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "toDate", description = "검색종료일 (YYYY-MM-DD)", schema = @Schema(type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "searchCd", description = "검색조건: ['']전체, [10]주문번호, [20]대표상품명, [30]주문자명", schema = @Schema(allowableValues = { "10", "20", "30" }, type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "searchKeyword", description = "검색어", schema = @Schema(type = "string", maxLength = 9999), in = ParameterIn.QUERY),
            @Parameter(name = "deliveryCode", description = "주문상태명 (공통코드 [10003] , 주문생성[CRT], 주문완료[COM], 주문취소[CAN])", schema = @Schema(allowableValues = { "CRT", "COM" , "CAN" }, type = "String", maxLength = 3), in = ParameterIn.QUERY),
            @Parameter(name = "page", description = "페이지번호", schema = @Schema(type = "integer", defaultValue = "0"), in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "페이지당 행 개수", schema = @Schema(type = "integer", defaultValue = "10"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "주문 목록 조회", description = "주문 목록을 전체 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 목록 조회 성공", content = @Content(schema = @Schema(implementation = ResOrderListDto.class)))
    })
    public SuccessResponse searchOrderList(
            @Parameter(hidden = true) @Valid ReqOrderSearchFilter reqOrderSearchFilter,
            @Parameter(hidden = true) Pageable pageable
    ) {
        Page<ResOrderListDto> result = orderService.searchOrderList(reqOrderSearchFilter, pageable);
        return new SuccessResponse(result);
    }

    @GetMapping("/{orderNo}")
    @Operation(summary = "주문 단건 조회", description = "주문 단건을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "주문 단건 조회 성공", content = @Content(schema = @Schema(implementation = ResOrderDto.class)))
    })
    public SuccessResponse searchOrder(@PathVariable(name = "orderNo") Long orderNo) {
        ResOrderDto result = orderService.searchOrder(orderNo);
        return new SuccessResponse(result);
    }

    @PostMapping("/redis")
    @Operation(summary = "주문 redis 등록", description = "주문을 Redis dB에 등록한다.")
    public SuccessResponse saveOrderRedis(@RequestBody @Valid ReqSaveOrderDto reqSaveOrderDto) {
        String redisId = orderService.saveOrderRedis(reqSaveOrderDto);
        return new SuccessResponse(redisId);
    }

    @PostMapping("/create/{redisId}")
    @Operation(summary = "Redis 데이터를 활용한 주문 생성", description = "Redis 데이터를 활용하여 주문을 생성한다.")
    public SuccessResponse saveOrder(@PathVariable("redisId") String redisId) {
        Long orderNo = orderService.saveOrder(redisId);
        return new SuccessResponse(orderNo);
    }

//    @PostMapping("")
//    @Operation(summary = "주문 등록", description = "주문을 등록한다.")
//    public SuccessResponse saveOrder(@RequestBody @Valid ReqSaveOrderDto reqSaveOrderDto) {
//        Long orderNo = orderService.saveOrder(reqSaveOrderDto);
//        return new SuccessResponse(orderNo);
//    }

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