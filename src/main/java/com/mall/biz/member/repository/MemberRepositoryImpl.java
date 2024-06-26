package com.mall.biz.member.repository;

import com.mall.biz.member.dto.req.ReqMemberSearchFilter;
import com.mall.biz.member.dto.res.QResMemberListDto;
import com.mall.biz.member.dto.res.ResMemberListDto;
import com.mall.biz.member.entity.Member;
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

import static com.mall.biz.member.entity.QMember.member;
import static org.springframework.util.StringUtils.hasText;

public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ResMemberListDto> searchMemberList(ReqMemberSearchFilter reqMemberSearchFilter, Pageable pageable) {
             List<ResMemberListDto> content = queryFactory
                .select(new QResMemberListDto(
                        member.id.as("memberId"),
                        member.loginId,
                        member.name,
                        member.age,
                        member.phone,
                        member.address,
                        member.zipCode
                ))
                .from(member)
                .where(
                        usernameEq(reqMemberSearchFilter),
                        loginIdEq(reqMemberSearchFilter),
                        betweenDate(reqMemberSearchFilter)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Member> countQuery = queryFactory
                .select(member)
                .from(member)
                .where(
                        usernameEq(reqMemberSearchFilter),
                        loginIdEq(reqMemberSearchFilter),
                        betweenDate(reqMemberSearchFilter)
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression usernameEq(ReqMemberSearchFilter reqMemberSearchFilter) {
        if (reqMemberSearchFilter.getSearchCd() != null
                && reqMemberSearchFilter.getSearchCd().equals("10")) {
            return hasText(reqMemberSearchFilter.getSearchKeyword()) ? member.name.like('%' + reqMemberSearchFilter.getSearchKeyword() + '%') : null;
        }
        return null;
    }

    private BooleanExpression loginIdEq(ReqMemberSearchFilter reqMemberSearchFilter) {
        if (reqMemberSearchFilter.getSearchCd() != null
                && reqMemberSearchFilter.getSearchCd().equals("20")) {
            return hasText(reqMemberSearchFilter.getSearchKeyword()) ? member.loginId.like('%' + reqMemberSearchFilter.getSearchKeyword() + '%') : null;
        }
        return null;
    }

    private BooleanExpression betweenDate(ReqMemberSearchFilter reqMemberSearchFilter) {
        if (!(reqMemberSearchFilter.getFromDate() == null) && !reqMemberSearchFilter.getFromDate().isBlank() &&
                !(reqMemberSearchFilter.getToDate() == null) && !reqMemberSearchFilter.getToDate().isBlank()) {
            String tempFromDate = reqMemberSearchFilter.getFromDate().replace(" ", "T");
            LocalDateTime fromDate = LocalDateTime.parse(tempFromDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String tempToDate = reqMemberSearchFilter.getToDate().replace(" ", "T");
            LocalDateTime toDate = LocalDateTime.parse(tempToDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return member.createDate.between(fromDate, toDate);
        }
        return null;
    }
}
