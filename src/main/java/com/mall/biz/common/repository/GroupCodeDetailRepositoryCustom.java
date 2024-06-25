package com.mall.biz.common.repository;


import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupCodeDetailRepositoryCustom {
    String findGroupCodeDetail(String groupCode, String detailCode);
}
