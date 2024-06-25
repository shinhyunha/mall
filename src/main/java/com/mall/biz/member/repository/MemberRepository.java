package com.mall.biz.member.repository;

import com.mall.biz.member.dto.req.ReqMemberSearchFilter;
import com.mall.biz.member.entity.Member;
import com.mall.biz.member.dto.res.ResMemberListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryCustom, QuerydslPredicateExecutor<Member> {
    Long countById(String memberId);
}
