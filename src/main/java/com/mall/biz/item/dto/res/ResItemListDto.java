package com.mall.biz.item.dto.res;

import com.mall.biz.item.entity.CategoryCode;
import com.mall.biz.item.entity.GenderCode;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class ResItemListDto {
    @Schema(description = "상품아이디")
    private Long itemNo;

    @Schema(description = "상품명")
    private String name;

    @Schema(description = "상품가격")
    private int price;

    @Schema(description = "상품수량)")
    private int quantity;

    @Schema(description = "상품 카테고리코드 (공통코드 [10001] , 상의[TOP], 하의[BOT], 원피스[ONE])")
    private CategoryCode categoryCode;

    @Schema(description = "상품 카테고리명 (공통코드 [10001] , 상의[TOP], 하의[BOT], 원피스[ONE])")
    private String categoryName;

    @Schema(description = "성별 종류코드 (공통코드 [10002] , 남성[MAL], 여성[FMA], 공용[UNS])")
    private GenderCode genderCode;

    @Schema(description = "성별 종류코드 (공통코드 [10002] , 남성[MAL], 여성[FMA], 공용[UNS])")
    private String genderName;

    @QueryProjection
    public ResItemListDto(Long itemNo, String name, int price, int quantity, CategoryCode categoryCode, GenderCode genderCode) {
        this.itemNo = itemNo;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryCode = categoryCode;
        this.genderCode = genderCode;
    }

    public void saveCodeName(String categoryName, String genderName) {
        this.categoryName = categoryName;
        this.genderName = genderName;
    }
}
