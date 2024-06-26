package com.mall.biz.item.dto.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.biz.item.entity.CategoryCode;
import com.mall.biz.item.entity.GenderCode;
import com.mall.biz.item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReqItemQuantityDto {
    @NotBlank(message = "상품 번호는 {valid.notblank}")
    @Schema(description = "상품 번호", type = "Long")
    private Long id;

    @NotBlank(message = "수량은 {valid.notblank}")
    @Schema(description = "수량", type = "int")
    private int quantity;
}
