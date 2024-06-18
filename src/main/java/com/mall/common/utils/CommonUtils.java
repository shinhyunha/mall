package com.mall.common.utils;

import java.util.UUID;

public final class CommonUtils {// 고유아이디 생성
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
