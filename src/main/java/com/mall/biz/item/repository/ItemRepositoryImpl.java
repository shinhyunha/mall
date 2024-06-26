package com.mall.biz.item.repository;

import com.mall.biz.item.dto.req.ReqItemSearchFilter;
import com.mall.biz.item.dto.res.QResItemListDto;
import com.mall.biz.item.dto.res.ResItemListDto;
import com.mall.biz.item.entity.Item;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.mall.biz.item.entity.QItem.*;
import static org.springframework.util.StringUtils.hasText;

public class ItemRepositoryImpl implements ItemRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ResItemListDto> searchItemList(ReqItemSearchFilter reqItemSearchFilter, Pageable pageable) {
        List<ResItemListDto> content = queryFactory
                .select(new QResItemListDto(
                        item.id.as("itemNo"),
                        item.name,
                        item.price,
                        item.quantity,
                        item.categoryCode,
                        item.genderCode
                ))
                .from(item)
                .where(
                        itemNameEq(reqItemSearchFilter),
                        categoryEq(reqItemSearchFilter),
                        genderEq(reqItemSearchFilter),
                        betweenDate(reqItemSearchFilter)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(item.id.desc())
                .fetch();

        JPAQuery<Item> countQuery = queryFactory
                .select(item)
                .from(item)
                .where(
                        itemNameEq(reqItemSearchFilter),
                        categoryEq(reqItemSearchFilter),
                        genderEq(reqItemSearchFilter),
                        betweenDate(reqItemSearchFilter)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression itemNameEq(ReqItemSearchFilter reqItemSearchFilter) {
        if (reqItemSearchFilter.getSearchKeyword() != null) {
            return hasText(reqItemSearchFilter.getSearchKeyword()) ? item.name.like('%' + reqItemSearchFilter.getSearchKeyword() + '%') : null;
        }
        return null;
    }

    private BooleanExpression categoryEq(ReqItemSearchFilter reqItemSearchFilter) {
        if (reqItemSearchFilter.getCategoryCode() != null) {
            return hasText(String.valueOf(reqItemSearchFilter.getCategoryCode())) ? item.categoryCode.eq(reqItemSearchFilter.getCategoryCode()) : null;
        }
        return null;
    }

    private BooleanExpression genderEq(ReqItemSearchFilter reqItemSearchFilter) {
        if (reqItemSearchFilter.getGenderCode() != null) {
            return hasText(String.valueOf(reqItemSearchFilter.getGenderCode())) ? item.genderCode.eq(reqItemSearchFilter.getGenderCode()) : null;
        }
        return null;
    }

    private BooleanExpression betweenDate(ReqItemSearchFilter reqItemSearchFilter) {
        if (!(reqItemSearchFilter.getFromDate() == null) &&
                !reqItemSearchFilter.getFromDate().isBlank() &&
                !(reqItemSearchFilter.getToDate() == null) &&
                !reqItemSearchFilter.getToDate().isBlank()) {
            String tempFromDate = reqItemSearchFilter.getFromDate().replace(" ", "T");
            LocalDateTime fromDate = LocalDateTime.parse(tempFromDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String tempToDate = reqItemSearchFilter.getToDate().replace(" ", "T");
            LocalDateTime toDate = LocalDateTime.parse(tempToDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return item.createDate.between(fromDate, toDate);
        }
        return null;
    }

}
