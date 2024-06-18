package com.mall.biz.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mall.biz.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
public class ReqSaveMemberDto {
    @NotBlank(message = "로그인 아이디는 {valid.notblank}")
    @Schema(description = "로그인 아이디", type = "String")
    private String loginId;

    @NotBlank(message = "비밀번호는 {valid.notblank}")
    @Schema(description = "비밀번호", type = "String")
    private String password;

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

    @JsonIgnore
    private String id;

    public Member dtoToEntity() {
        return Member.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .name(name)
                .age(age)
                .phone(phone.replace("-", ""))
                .address(address)
                .zipCode(zipCode)
                .build();
    }

    public void registMemberId(String memberId) {
        this.id = memberId;
    }
}
