package com.mall.biz.sample.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "sample", timeToLive = 1000)
public class SampleRedis {
    @Id
    private Long id;

    private String name;

    public SampleRedis(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
