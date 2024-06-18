package com.mall.biz.member.entity;

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
}
