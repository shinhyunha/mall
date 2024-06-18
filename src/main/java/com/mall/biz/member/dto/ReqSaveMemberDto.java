package com.mall.biz.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
public class ReqSaveMemberDto {
    @NotBlank(message = "현재 비밀번호는 {valid.notblank}")
    @Schema(description = "로그인 아이디", type = "String")
    private String loginId;

    @Schema(description = "비밀번호", type = "String")
    private String password;

    @Schema(description = "이름", type = "String")
    private String name;

    @Schema(description = "나이", type = "int")
    private int age;

    @Schema(description = "전화번호(-없이)", type = "String")
    private String phone;

    @Schema(description = "주소", type = "int")
    private String address;

    @Schema(description = "우편번호", type = "int")
    private String zipCode;
}
