package com.mall.biz.member.dto.res;

import com.mall.biz.member.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ResMemberDto {
    private String memberId;
    private String loginId;
    private String name;
    private int age;
    private String phone;
    private String address;
    private String zipCode;

    public ResMemberDto(Member member) {
        this.memberId = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.age = member.getAge();
        this.phone = member.getPhone();
        this.address = member.getAddress();
        this.zipCode = member.getZipCode();
    }
}
