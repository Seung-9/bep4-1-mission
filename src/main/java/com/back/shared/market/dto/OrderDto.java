package com.back.shared.market.dto;

import com.back.boundedcontext.market.domain.Order;
import com.back.standard.modeltype.CanGetModelTypeCode;
import java.time.LocalDateTime;

public record OrderDto (
    int id,
    LocalDateTime createDate,
    LocalDateTime modifyDate,
    int customerId,
    String customerName,
    long price,
    long salePrice,
    LocalDateTime requestPaymentDate,
    LocalDateTime paymentDate
) implements CanGetModelTypeCode {
    public static OrderDto of(Order order) {
        return new OrderDto(order.getId(), order.getCreateDate(), order.getModifyDate(), order.getBuyer().getId(),
                order.getBuyer().getUsername(), order.getPrice(), order.getSalePrice(),
                order.getRequestPaymentDate(), order.getPaymentDate());
    }

    @Override
    public String getModelTypeCode() {
        return "Order";
    }
}