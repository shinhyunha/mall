package com.mall.common.model;

import com.mall.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse {
    @Schema(description = "결과코드", type = "String")
    private String resultCode;
    @Schema(description = "결과메세지", type = "String")
    private String resultMessage;

    @Schema(description = "결과값", type = "Object")
    private Object result;

    public SuccessResponse() {
        this.resultCode = Const.ResponseCode.SUCCESS.getCode();
        this.resultMessage = Const.ResponseCode.SUCCESS.getMessage();
    }

    public SuccessResponse(Object result) {
        this.resultCode = Const.ResponseCode.SUCCESS.getCode();
        this.resultMessage = Const.ResponseCode.SUCCESS.getMessage();
        this.result = result;
    }
}
