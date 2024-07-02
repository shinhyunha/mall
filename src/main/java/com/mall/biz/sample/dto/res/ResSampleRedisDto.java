package com.mall.biz.sample.dto.res;

import lombok.Data;

@Data
public class ResSampleRedisDto {
    private Long id;
    private String name;

    public ResSampleRedisDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
