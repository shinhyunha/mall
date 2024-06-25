package com.mall.biz.common.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.mall.common.entity.QGroupCodeDetailEntity.*;

public class GroupCodeDetailRepositoryImpl implements GroupCodeDetailRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public GroupCodeDetailRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public String findGroupCodeDetail(String groupCode, String detailCode) {
        return queryFactory
                .select(groupCodeDetailEntity.code_data)
                .from(groupCodeDetailEntity)
                .where(
                        groupCodeDetailEntity.groupCode.eq(groupCode),
                        groupCodeDetailEntity.detailCode.eq(detailCode)
                )
                .fetchOne();
    }
}
