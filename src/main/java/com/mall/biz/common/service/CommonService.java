package com.mall.biz.common.service;

import com.mall.biz.common.repository.GroupCodeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonService {
    private final GroupCodeDetailRepository groupCodeDetailRepository;

    public String searchGroupDetailName(String groupCode, String detailCode) {
        return groupCodeDetailRepository.findGroupCodeDetail(groupCode, detailCode);
    }
}
