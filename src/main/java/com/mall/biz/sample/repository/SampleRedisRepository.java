package com.mall.biz.sample.repository;

import com.mall.biz.sample.entity.SampleRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRedisRepository extends CrudRepository<SampleRedis, Long> {
}
