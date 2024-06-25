package com.mall.biz.item.dto.res;

import com.mall.biz.item.entity.CategoryCode;
import com.mall.biz.item.entity.GenderCode;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResItemListDto {
    private Long itemId;
    private String name;
    private int price;
    private int quantity;
    private CategoryCode categoryCode;
    private String categoryName;
    private GenderCode genderCode;
    private String genderName;

    @QueryProjection
    public ResItemListDto(Long itemId, String name, int price, int quantity, CategoryCode categoryCode, GenderCode genderCode) {
        this.itemId = itemId;
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
