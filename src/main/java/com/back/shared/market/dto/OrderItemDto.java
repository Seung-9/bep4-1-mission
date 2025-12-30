package com.back.shared.market.dto;

import com.back.boundedcontext.market.domain.OrderItem;
import java.time.LocalDateTime;

public record OrderItemDto(
        int id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        int orderId,
        int buyerId,
        String buyerName,
        int sellerId,
        String sellerName,
        int productId,
        String productName,
        long price,
        long salePrice,
        double payoutRate
) {
    public static OrderItemDto of(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getCreateDate(),
                orderItem.getModifyDate(),
                orderItem.getOrder().getId(),
                orderItem.getOrder().getBuyer().getId(),
                orderItem.getOrder().getBuyer().getUsername(),
                orderItem.getProduct().getSeller().getId(),
                orderItem.getProduct().getSeller().getUsername(),
                orderItem.getProduct().getId(),
                orderItem.getProductName(),
                orderItem.getPrice(),
                orderItem.getSalePrice(),
                orderItem.getPayoutRate()
        );
    }
}
