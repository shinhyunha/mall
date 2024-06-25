package com.mall.biz.delivery.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.biz.delivery.entity.Delivery;
import com.mall.biz.delivery.entity.DeliveryCode;
import com.mall.biz.order.entity.Order;
import com.mall.common.entity.BaseDateEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ReqDeliveryDto {
    @Schema(description = "주문 번호", type = "Long")
    private Long orderNo;

    @Schema(description = "수신자 이름", type = "String")
    private String receiverName;

    @Schema(description = "배송지 주소", type = "String")
    private String deliveryAddress;

    @Schema(description = "우편번호", type = "String")
    private String zipCode;

    @Schema(description = "수신자 연락처", type = "String")
    private String phone;

    @JsonIgnore
    private DeliveryCode deliveryCode;

    public Delivery dtoToEntity(Order order) {
        return new Delivery(
                order,
                this.deliveryCode,
                this.receiverName,
                this.deliveryAddress,
                this.zipCode,
                this.phone
        );
    }

    public void changeDeliverCode(DeliveryCode deliveryCode) {
        this.deliveryCode = deliveryCode;
    }
}
