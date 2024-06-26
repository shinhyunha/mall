package com.mall.biz.order.dto.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mall.biz.member.entity.Member;
import com.mall.biz.order.entity.Order;
import com.mall.biz.order.entity.OrderPurchaser;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
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

    @Schema(description = "이름", type = "String")
    private String name;

    @Pattern(regexp = "^[\\w.%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{valid.email}")
    @Schema(description = "이메일", type = "String")
    private String email;

    @Schema(description = "전화번호(-없이)", type = "String")
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
