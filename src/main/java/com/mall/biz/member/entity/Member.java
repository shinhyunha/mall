package com.mall.biz.member.entity;

import com.mall.common.entity.BaseDateEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
