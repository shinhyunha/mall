package com.mall.biz.member.repository;


import com.mall.biz.member.dto.req.ReqMemberSearchFilter;
import com.mall.biz.member.dto.res.ResMemberListDto;
import com.mall.biz.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepositoryCustom {
    Page<ResMemberListDto> searchMemberList(ReqMemberSearchFilter reqMemberSearchFilter, Pageable pageable);
}
