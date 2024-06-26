package com.mall.biz.delivery.dto.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.biz.delivery.entity.DeliveryCode;
import com.mall.biz.item.entity.CategoryCode;
import com.mall.biz.item.entity.GenderCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReqDeliverySearchFilter {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "검색시작일 (YYYY-MM-DD)", type = "String", nullable = true)
    private String fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "검색종료일 (YYYY-MM-DD)", type = "String", nullable = true)
    private String toDate;

    @Schema(description = "검색조건: ['']전체, [10]배송번호, [20]대표상품명", allowableValues = { "10", "20" }, type = "String", nullable = true)
    private String searchCd;

    @Schema(description = "검색어", type = "String", nullable = true, maxLength = 9999)
    private String searchKeyword;

    @Schema(description = "배송상태코드 (공통코드 [10004] , 배송생성[CRT], 배송중[ING], 배송완료[COM], 배송취소[CAN])", type = "CategoryCode")
    private DeliveryCode deliveryCode;
}
