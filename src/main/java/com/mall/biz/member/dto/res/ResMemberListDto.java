package com.mall.biz.member.dto.res;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ResMemberListDto {
    private String memberId;
    private String loginId;
    private String password;
    private String name;
    private int age;
    private String phone;
    private String address;
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
