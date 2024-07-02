package com.mall.biz.sample.dto.req;

import com.mall.biz.sample.entity.SampleRedis;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRedisSampleDto {
    private Long id;
    private String name;
}
