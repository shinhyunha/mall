package com.mall.biz.sample.dto.res;

import com.mall.biz.item.entity.CategoryCode;
import com.mall.biz.item.entity.GenderCode;
import com.mall.biz.item.entity.Item;
import lombok.Data;

@Data
public class ResItemDto {
    private Long itemNo;
    private String name;
    private int price;
    private int quantity;
    private CategoryCode categoryCode;
    private String categoryName;
    private GenderCode genderCode;
    private String genderName;

    public ResItemDto(Item item) {
        this.itemNo = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.categoryCode = item.getCategoryCode();
        this.genderCode = item.getGenderCode();
    }

    public void saveCodeName(String categoryName, String genderName) {
        this.categoryName = categoryName;
        this.genderName = genderName;
    }
}
