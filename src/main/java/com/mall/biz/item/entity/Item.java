package com.mall.biz.item.entity;

import com.mall.biz.item.dto.req.ReqUpdateItemDto;
import com.mall.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "item")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    private Long id;
    private String name;
    private int price;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private CategoryCode categoryCode;

    @Enumerated(EnumType.STRING)
    private GenderCode genderCode;

    public Item(Long id, String name, int price, int quantity, CategoryCode categoryCode, GenderCode genderCode) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryCode = categoryCode;
        this.genderCode = genderCode;
    }

    // 상품 update
    public void updateItem(ReqUpdateItemDto reqUpdateItemDto) {
        this.id = reqUpdateItemDto.getId();
        this.name = reqUpdateItemDto.getName();
        this.price = reqUpdateItemDto.getPrice();
        this.quantity = reqUpdateItemDto.getQuantity();
        this.categoryCode = reqUpdateItemDto.getCategoryCode();
        this.genderCode = reqUpdateItemDto.getGenderCode();
        this.updateBaseEntity();
    }
}
