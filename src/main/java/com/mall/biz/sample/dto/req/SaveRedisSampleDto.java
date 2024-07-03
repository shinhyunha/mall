package com.mall.biz.sample.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mall.biz.sample.entity.SampleRedis;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;

@Data
@NoArgsConstructor
public class SaveRedisSampleDto {
    private Long id;
    private String name;

    public SampleRedis dtoToEntity() {
        return new SampleRedis(this.id, this.name);
    }
}
