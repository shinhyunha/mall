package com.mall.common.utils;

import com.mall.common.exception.InputCheckException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;

public class ValidUtils {
    // LocatDatetime 형식
    public static void validBetweenDate(LocalDateTime beginDate, LocalDateTime endDate) {
        if (beginDate == null || endDate == null) {
            return;
        }

        if (beginDate.isAfter(endDate)) {
            throw new InputCheckException("시작일이 종료일보다 클 수 없습니다.");
        }
    }

    // Date 형식
    public static void validBetweenDate(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            return;
        }

        if (beginDate.getTime() - endDate.getTime() > 0) {
            throw new InputCheckException("시작일이 종료일보다 클 수 없습니다.");
        }
    }

    // YYYY-MM-DD or YYYYMMDD 형식
    public static void validBetweenDate(String beginDate, String endDate) {
        if (StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate)) {
            return;
        }

        if (Integer.parseInt(beginDate.replace("-", "")) - Integer.parseInt(endDate.replace("-", "")) > 0) {
            throw new InputCheckException("시작일자가 종료일자보다 클 수 없습니다.");
        }
    }
}
