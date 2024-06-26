package com.mall.biz.item.dto.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.biz.item.entity.CategoryCode;
import com.mall.biz.item.entity.GenderCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReqItemSearchFilter {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "검색시작일 (YYYY-MM-DD)", type = "String", nullable = true)
    private String fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "검색종료일 (YYYY-MM-DD)", type = "String", nullable = true)
    private String toDate;

    @Schema(description = "상품명 검색어", type = "String", nullable = true, maxLength = 9999)
    private String searchKeyword;

    @Schema(description = "상품 카테고리코드 (공통코드 [10001] , 상의[TOP], 하의[BOT], 원피스[ONE])", type = "CategoryCode")
    private CategoryCode categoryCode;

    @Schema(description = "성별 종류코드 (공통코드 [10002] , 남성[MAL], 여성[FMA], 공용[UNS])", type = "GenderCode")
    private GenderCode genderCode;
}
