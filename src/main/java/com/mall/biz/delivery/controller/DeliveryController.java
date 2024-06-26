package com.mall.biz.delivery.controller;

import com.mall.biz.delivery.dto.req.ReqDeliveryDto;
import com.mall.biz.delivery.dto.req.ReqDeliverySearchFilter;
import com.mall.biz.delivery.dto.req.ReqUpdateDeliveryDto;
import com.mall.biz.delivery.dto.res.ResDeliveryDto;
import com.mall.biz.delivery.dto.res.ResDeliveryListDto;
import com.mall.biz.delivery.service.DeliveryService;
import com.mall.biz.item.dto.req.ReqItemSearchFilter;
import com.mall.biz.item.dto.res.ResItemListDto;
import com.mall.biz.member.dto.req.ReqUpdateMemberDto;
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
@RequestMapping("/api/delivery")
@Tag(name="배송", description = "배송 API")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("")
    @Parameters({
            @Parameter(name = "fromDate", description = "검색시작일 (YYYY-MM-DD)", schema = @Schema(type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "toDate", description = "검색종료일 (YYYY-MM-DD)", schema = @Schema(type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "searchCd", description = "검색조건: ['']전체, [10]배송번호, [20]대표상품명", schema = @Schema(allowableValues = { "10", "20" }, type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "searchKeyword", description = "검색어", schema = @Schema(type = "string", maxLength = 9999), in = ParameterIn.QUERY),
            @Parameter(name = "deliveryCode", description = "배송상태코드 (공통코드 [10004] , 배송생성[CRT], 배송중[ING], 배송완료[COM], 배송취소[CAN])", schema = @Schema(allowableValues = { "CRT", "ING", "COM" , "CAN" }, type = "String", maxLength = 3), in = ParameterIn.QUERY),
            @Parameter(name = "page", description = "페이지번호", schema = @Schema(type = "integer", defaultValue = "0"), in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "페이지당 행 개수", schema = @Schema(type = "integer", defaultValue = "10"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "배송 목록 조회", description = "배송 목록을 전체 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "배송 목록 조회 성공", content = @Content(schema = @Schema(implementation = ResDeliveryListDto.class)))
    })
    public SuccessResponse searchItemList(
            @Parameter(hidden = true) @Valid ReqDeliverySearchFilter reqDeliverySearchFilter,
            @Parameter(hidden = true) Pageable pageable
    ) {
        Page<ResDeliveryListDto> result = deliveryService.searchDeliveryList(reqDeliverySearchFilter, pageable);
        return new SuccessResponse(result);
    }

    @GetMapping("/{deliverNo}")
    @Operation(summary = "배송 단건 조회", description = "배송번호로 상세 조회한다.")
    public SuccessResponse searchDelivery(@PathVariable("deliverNo") Long deliverNo) {
        ResDeliveryDto result = deliveryService.searchDelivery(deliverNo);
        return new SuccessResponse(result);
    }

    @PostMapping("")
    @Operation(summary = "배송 등록", description = "배송을 등록한다.")
    public SuccessResponse saveDelivery(@RequestBody @Valid ReqDeliveryDto reqDeliveryDto) {
        deliveryService.saveDelivery(reqDeliveryDto);
        return new SuccessResponse();
    }

    @PostMapping("/ongoing/{deliveryNo}")
    @Operation(summary = "배송 진행 상태 변경", description = "배송을 진행상태로 변경한다.")
    public SuccessResponse updateOngoingDeliveryStatus(@PathVariable("deliveryNo") Long deliveryNo) {
        deliveryService.updateOngoingDeliveryStatus(deliveryNo);
        return new SuccessResponse();
    }

    @PostMapping("/complete/{deliveryNo}")
    @Operation(summary = "배송 완료 상태 변경", description = "배송을 완료상태로 변경한다.")
    public SuccessResponse updateCompleteDeliveryStatus(@PathVariable("deliveryNo") Long deliveryNo) {
        deliveryService.updateCompleteDeliveryStatus(deliveryNo);
        return new SuccessResponse();
    }

    @PostMapping("/cancel/{deliveryNo}")
    @Operation(summary = "배송 취소 상태 변경", description = "배송을 취소상태로 변경한다.")
    public SuccessResponse updateCancelDeliveryStatus(@PathVariable("deliveryNo") Long deliveryNo) {
        deliveryService.updateCancelDeliveryStatus(deliveryNo);
        return new SuccessResponse();
    }

    @PutMapping("")
    @Operation(summary = "배송 정보 수정", description = "배송정보를 수정한다.")
    public SuccessResponse updateDelivery(@RequestBody ReqUpdateDeliveryDto reqUpdateDeliveryDto) {
        deliveryService.updateDeliveryInformation(reqUpdateDeliveryDto);
        return new SuccessResponse();
    }
}
