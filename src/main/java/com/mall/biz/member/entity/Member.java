package com.mall.biz.member.entity;

import com.mall.biz.member.dto.req.ReqUpdateMemberDto;
import com.mall.common.entity.BaseDateEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "member")
public class Member extends BaseDateEntity {
    @Id
    @Column(name = "member_id")
    private String id;
    private String loginId;
    private String password;
    private String name;
    private int age;
    private String phone;
    private String address;
    private String zipCode;

    public Member(String id, String loginId, String password, String name, int age, String phone, String address, String zipCode) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.zipCode = zipCode;
    }

    // 로그인 아이디 및 비밀번호 체크
    public boolean validtionLoginIdPassword(String loginId, String password) {
        return this.loginId.equals(loginId) && this.password.equals(password);
    }

    // 회원정보 업데이트
    public void updateMember(ReqUpdateMemberDto reqUpdateMemberDto) {
        this.name = reqUpdateMemberDto.getName();
        this.age = reqUpdateMemberDto.getAge();
        this.phone = reqUpdateMemberDto.getPhone();
        this.address = reqUpdateMemberDto.getAddress();
        this.zipCode = reqUpdateMemberDto.getZipCode();
        this.updateBaseEntity();
    }
}
