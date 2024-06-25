package com.mall.biz.item.repository;


import com.mall.biz.item.dto.req.ReqItemSearchFilter;
import com.mall.biz.item.dto.res.ResItemListDto;
import com.mall.biz.member.dto.req.ReqMemberSearchFilter;
import com.mall.biz.member.dto.res.ResMemberListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositoryCustom {
    Page<ResItemListDto> searchItemList(ReqItemSearchFilter reqItemSearchFilter, Pageable pageable);
}
