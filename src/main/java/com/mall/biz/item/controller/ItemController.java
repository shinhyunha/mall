package com.mall.biz.item.controller;

import com.mall.biz.item.dto.req.ReqItemSearchFilter;
import com.mall.biz.item.dto.req.ReqSaveItemDto;
import com.mall.biz.item.dto.req.ReqUpdateItemDto;
import com.mall.biz.item.dto.res.ResItemListDto;
import com.mall.biz.item.service.ItemService;
import com.mall.biz.member.dto.req.ReqMemberSearchFilter;
import com.mall.biz.member.dto.req.ReqSaveMemberDto;
import com.mall.biz.member.dto.res.ResMemberDto;
import com.mall.biz.member.dto.res.ResMemberListDto;
import com.mall.biz.member.service.MemberService;
import com.mall.biz.sample.dto.res.ResItemDto;
import com.mall.biz.sample.dto.res.ResSampleDto;
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
@RequestMapping("/api/item")
@Tag(name="상품", description = "상품 API")
public class ItemController {
    private final ItemService itemService;

    @PostMapping("")
    @Operation(summary = "상품 등록", description = "상품을 등록한다.")
    public SuccessResponse saveItem(@RequestBody @Valid ReqSaveItemDto reqSaveItemDto) {
        itemService.saveItem(reqSaveItemDto);
        return new SuccessResponse();
    }

    @GetMapping("")
    @Parameters({
            @Parameter(name = "fromDate", description = "검색시작일 (YYYY-MM-DD)", schema = @Schema(type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "toDate", description = "검색종료일 (YYYY-MM-DD)", schema = @Schema(type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "searchKeyword", description = "검색어(상품명)", schema = @Schema(type = "string", maxLength = 9999), in = ParameterIn.QUERY),
            @Parameter(name = "categoryCode", description = "상품카테고리", schema = @Schema(allowableValues = { "TOP", "BOT", "ONE" }, type = "String", maxLength = 3), in = ParameterIn.QUERY),
            @Parameter(name = "genderCode", description = "성별코드", schema = @Schema(allowableValues = { "MAL", "FMA", "UNS" }, type = "string", maxLength = 3), in = ParameterIn.QUERY),
            @Parameter(name = "page", description = "페이지번호", schema = @Schema(type = "integer", defaultValue = "0"), in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "페이지당 행 개수", schema = @Schema(type = "integer", defaultValue = "10"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "상품 목록 조회", description = "상품 목록을 전체 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상품 목록 조회 성공", content = @Content(schema = @Schema(implementation = ResItemListDto.class)))
    })
    public SuccessResponse searchItemList(
            @Parameter(hidden = true) @Valid ReqItemSearchFilter reqItemSearchFilter,
            @Parameter(hidden = true) Pageable pageable
    ) {
        Page<ResItemListDto> result = itemService.searchItemList(reqItemSearchFilter, pageable);
        return new SuccessResponse(result);
    }

    @GetMapping("/{itemId}")
    @Operation(summary = "상품 단건 조회", description = "상품 단건을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "상품 단건 조회 성공", content = @Content(schema = @Schema(implementation = ResItemDto.class)))
    })
    public SuccessResponse searchMemberList(@PathVariable(name = "itemId") Long itemId) {
        ResItemDto result = itemService.findItem(itemId);
        return new SuccessResponse(result);
    }

    @PutMapping("")
    @Operation(summary = "상품정보 수정", description = "상품정보를 수정한다.")
    public SuccessResponse updateItem(@RequestBody ReqUpdateItemDto reqUpdateItemDto) {
        itemService.updateItem(reqUpdateItemDto);
        return new SuccessResponse();
    }

}
