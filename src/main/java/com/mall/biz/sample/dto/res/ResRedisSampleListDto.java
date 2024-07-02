package com.mall.biz.sample.dto.res;

import com.mall.biz.sample.entity.SampleRedis;
import lombok.Data;

@Data
public class ResRedisSampleListDto {
    private Long id;
    private String name;

    public ResRedisSampleListDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
