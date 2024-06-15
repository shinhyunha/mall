package com.mall.biz.sample.repository;

import com.mall.biz.sample.entity.QSample;
import com.mall.biz.sample.entity.Sample;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SampleRepositoryImpl implements SampleRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public SampleRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Sample findByName(String name) {
        QSample sample = new QSample("sample");

        return queryFactory
                .select(sample)
                .from(sample)
                .where(sample.name.like('%' + name+ '%'))
                .fetchOne();
    }

    @Override
    public List<Sample> findAllJoinTeam() {
        QSample sample = new QSample("sample");

        return queryFactory
                .select(sample)
                .from(sample)
                .leftJoin(sample.sampleTeam)
                .orderBy(sample.id.desc())
                .fetch();
    }
}
