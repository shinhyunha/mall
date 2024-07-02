package com.mall.biz.sample.dto.req;

import com.mall.biz.sample.entity.Sample;
import com.mall.biz.sample.entity.SampleRedis;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveRedisSampleDto {
    private Long id;
    private String name;

    public SampleRedis dtoToEntity() {
        return new SampleRedis(this.id, this.name);
    }
}
