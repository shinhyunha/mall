package com.mall.biz.member.dto.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReqMemberSearchFilter {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "검색시작일 (YYYY-MM-DD)", type = "String", nullable = true)
    private String fromDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "검색종료일 (YYYY-MM-DD)", type = "String", nullable = true)
    private String toDate;

    @Schema(description = "검색조건: ['']전체, [10]회원명, [20]로그인ID", allowableValues = { "10", "20" }, type = "String", nullable = true)
    private String searchCd;

    @Schema(description = "검색어", type = "String", nullable = true, maxLength = 9999)
    private String searchKeyword;
}
