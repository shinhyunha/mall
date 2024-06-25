package com.mall.biz.order.dto.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.biz.member.entity.Member;
import com.mall.biz.order.entity.Order;
import com.mall.biz.order.entity.OrderPurchaser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderPurchaserDto {
    @Schema(description = "주문 번호", type = "Long")
    private Long orderNo;

    @Schema(description = "회원 번호", type = "String")
    private String memberId;

    private String name;
    private String email;
    private String phone;

    public OrderPurchaser dtoToEntity(Order order, Member member) {
        return new OrderPurchaser(
                order,
                member,
                this.getName(),
                this.getEmail(),
                this.getPhone());
    }
}
