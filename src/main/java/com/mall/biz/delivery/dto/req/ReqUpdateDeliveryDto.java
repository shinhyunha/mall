package com.mall.biz.delivery.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReqUpdateDeliveryDto {
    @NotBlank(message = "배송 번호는 {valid.notblank}")
    @Schema(description = "배송 번호", type = "String")
    private Long deliveryNo;

    @NotBlank(message = "수신자 이름는 {valid.notblank}")
    @Schema(description = "수신자 이름", type = "String")
    private String receiverName;

    @NotBlank(message = "배송지 주소는 {valid.notblank}")
    @Schema(description = "배송지 주소", type = "String")
    private String deliveryAddress;

    @NotBlank(message = "배송지 우편번호는 {valid.notblank}")
    @Schema(description = "배송지 우편번호", type = "String")
    private String zipCode;

    @NotBlank(message = "수신자 연락처는 {valid.notblank}")
    @Schema(description = "수신자 연락처", type = "String")
    private String phone;
}
