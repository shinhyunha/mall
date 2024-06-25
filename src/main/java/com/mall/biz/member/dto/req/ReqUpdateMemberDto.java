package com.mall.biz.member.dto.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.common.exception.InputCheckException;
import com.mall.common.utils.CryptoUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReqUpdateMemberDto {
    @NotBlank(message = "회원 번호는 {valid.notblank}")
    @Schema(description = "회원 번호", type = "String")
    private String id;

    @NotBlank(message = "로그인 아이디는 {valid.notblank}")
    @Schema(description = "로그인 아이디", type = "String")
    private String loginId;

    @NotBlank(message = "비밀번호는 {valid.notblank}")
    @Schema(description = "비밀번호", type = "String")
    private String password;
    public String getPassword() {
        try {
            this.password = CryptoUtil.digestText(this.password);
        } catch (Exception e) {
            throw new InputCheckException("내부 오류입니다.");
        }
        return this.password;
    }

    @NotBlank(message = "이름은 {valid.notblank}")
    @Schema(description = "이름", type = "String")
    private String name;

    @NotBlank(message = "나이는 {valid.notblank}")
    @Schema(description = "나이", type = "int")
    private int age;

    @NotBlank(message = "전화번호는 {valid.notblank}")
    @Schema(description = "전화번호(-없이)", type = "String")
    private String phone;

    @NotBlank(message = "주소는 {valid.notblank}")
    @Schema(description = "주소", type = "int")
    private String address;

    @NotBlank(message = "우편번호는 {valid.notblank}")
    @Schema(description = "우편번호", type = "int")
    private String zipCode;
}
