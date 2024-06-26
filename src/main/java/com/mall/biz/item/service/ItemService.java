package com.mall.biz.item.service;

import com.mall.biz.common.service.CommonService;
import com.mall.biz.item.dto.req.ReqItemQuantityDto;
import com.mall.biz.item.dto.req.ReqItemSearchFilter;
import com.mall.biz.item.dto.req.ReqSaveItemDto;
import com.mall.biz.item.dto.req.ReqUpdateItemDto;
import com.mall.biz.item.dto.res.ResItemListDto;
import com.mall.biz.item.entity.Item;
import com.mall.biz.item.repository.ItemRepository;
import com.mall.biz.item.dto.res.ResItemDto;
import com.mall.common.exception.InputCheckException;
import com.mall.common.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final CommonService commonService;

    @Transactional
    public void saveItem(ReqSaveItemDto reqSaveItemDto) {
        Item item = reqSaveItemDto.dtoToEntity();
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public Page<ResItemListDto> searchItemList(ReqItemSearchFilter reqItemSearchFilter, Pageable pageable) {
        // 날짜 검증
        if (!(reqItemSearchFilter.getFromDate() == null) && !reqItemSearchFilter.getFromDate().isBlank() &&
                !(reqItemSearchFilter.getToDate() == null) && !reqItemSearchFilter.getToDate().isBlank()) {
            String fromDate = reqItemSearchFilter.getFromDate();
            String toDate = reqItemSearchFilter.getToDate();
            ValidUtils.validBetweenDate(fromDate, toDate);
            reqItemSearchFilter.setFromDate(fromDate + " 00:00:00");
            reqItemSearchFilter.setToDate(toDate + " 23:59:59");
        }

        Page<ResItemListDto> result = itemRepository.searchItemList(reqItemSearchFilter, pageable);

        // 코드명 추가
        for (ResItemListDto resItemListDto : result) {
            String categoryName = commonService.searchGroupDetailName("10001", String.valueOf(resItemListDto.getCategoryCode()));
            String genderName = commonService.searchGroupDetailName("10002", String.valueOf(resItemListDto.getGenderCode()));
            resItemListDto.saveCodeName(categoryName, genderName);
        }

        return result;
    }

    @Transactional(readOnly = true)
    public ResItemDto findItem(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(()
                -> new InputCheckException("상품아이디를 확인하세요."));

        ResItemDto result = new ResItemDto(item);

        // 상품 카테고리, 성별 코드명 입력
        String categoryName = commonService.searchGroupDetailName("10001", String.valueOf(result.getCategoryCode()));
        String genderName = commonService.searchGroupDetailName("10002", String.valueOf(result.getGenderCode()));
        result.saveCodeName(categoryName, genderName);

        return result;
    }


    @Transactional
    public void updateItem(ReqUpdateItemDto reqUpdateItemDto) {
        // 상품번호 validation
        Item findItem = itemRepository.findById(reqUpdateItemDto.getId()).orElseThrow(()
                -> new InputCheckException("상품번호를 확인하세요."));

        // 상품 업데이트
        findItem.updateItem(reqUpdateItemDto);
    }

    @Transactional
    public void updateItemQuantity(ReqItemQuantityDto reqItemQuantityDto) {
        // 상품번호 체크
        Item item = itemRepository.findById(reqItemQuantityDto.getId()).orElseThrow(()
                -> new InputCheckException("상품번호를 확인하세요."));

        // 재고추가
        item.addQuantity(reqItemQuantityDto.getQuantity());
    }
}
