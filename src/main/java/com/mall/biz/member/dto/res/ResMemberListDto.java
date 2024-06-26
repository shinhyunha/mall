package com.mall.biz.member.dto.res;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResMemberListDto {
    @Schema(description = "회원 아이디")
    private String memberId;

    @Schema(description = "로그인 아이디")
    private String loginId;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "나이")
    private int age;

    @Schema(description = "전화번호 (- 제거)")
    private String phone;

    @Schema(description = "주소")
    private String address;

    @Schema(description = "우편번호")
    private String zipCode;

    @QueryProjection
    public ResMemberListDto(String memberId, String loginId, String name, int age, String phone, String address, String zipCode) {
        this.memberId = memberId;
        this.loginId = loginId;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.zipCode = zipCode;
    }
}
