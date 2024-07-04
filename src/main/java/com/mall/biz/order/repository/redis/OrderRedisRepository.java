package com.mall.biz.order.repository.redis;

import com.mall.biz.order.entity.redis.OrderRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRedisRepository extends CrudRepository<OrderRedis, String> {
}
