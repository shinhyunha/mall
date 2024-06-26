package com.mall.biz.order.dto.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.biz.delivery.entity.DeliveryCode;
import com.mall.biz.order.entity.OrderCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReqOrderSearchFilter {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "검색시작일 (YYYY-MM-DD)", type = "String", nullable = true)
    private String fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "검색종료일 (YYYY-MM-DD)", type = "String", nullable = true)
    private String toDate;

    @Schema(description = "검색조건: ['']전체, [10]주문번호, [20]대표상품명, [30]주문자명", allowableValues = { "10", "20", "30" }, type = "String", nullable = true)
    private String searchCd;

    @Schema(description = "검색어", type = "String", nullable = true, maxLength = 9999)
    private String searchKeyword;

    @Schema(description = "주문상태명 (공통코드 [10003] , 주문생성[CRT], 주문완료[COM], 주문취소[CAN])")
    private OrderCode orderCode;
}
