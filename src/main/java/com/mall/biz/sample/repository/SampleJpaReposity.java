package com.mall.biz.sample.repository;

import com.mall.biz.sample.entity.Sample;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class SampleJpaReposity {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public SampleJpaReposity(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
}
