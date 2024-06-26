package com.mall.biz.item.dto.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.biz.item.entity.CategoryCode;
import com.mall.biz.item.entity.GenderCode;
import com.mall.common.exception.InputCheckException;
import com.mall.common.utils.CryptoUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReqUpdateItemDto {
    @NotBlank(message = "상품 번호는 {valid.notblank}")
    @Schema(description = "상품 번호", type = "Long")
    private Long id;

    @NotBlank(message = "이름은 {valid.notblank}")
    @Schema(description = "이름", type = "String")
    private String name;

    @NotBlank(message = "상품가격은 {valid.notblank}")
    @Schema(description = "가격", type = "int")
    private int price;

    @NotBlank(message = "수량은 {valid.notblank}")
    @Schema(description = "수량", type = "int")
    private int quantity;

    @Schema(description = "상품 카테고리코드 (공통코드 [10001] , 상의[TOP], 하의[BOT], 원피스[ONE])", type = "CategoryCode")
    private CategoryCode categoryCode;

    @Schema(description = "성별 종류코드 (공통코드 [10002] , 남성[MAL], 여성[FMA], 공용[UNS])", type = "GenderCode")
    private GenderCode genderCode;
}
