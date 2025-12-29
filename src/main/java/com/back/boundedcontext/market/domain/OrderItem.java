package com.back.boundedcontext.market.domain;

import static jakarta.persistence.FetchType.LAZY;

import com.back.global.jpa.entity.BaseAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARKET_ORDER_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class OrderItem extends BaseAndTime {
    @ManyToOne(fetch = LAZY)
    private Order order;

    @ManyToOne(fetch = LAZY)
    private Product product;

    private String productName;

    private long price;

    private long salePrice;

    private double payoutRate = MarketPolicy.PRODUCT_PAYOUT_RATE;

    public static OrderItem create(Order order, Product product) {
        return OrderItem.builder()
                .order(order)
                .product(product)
                .productName(product.getName())
                .price(product.getPrice())
                .salePrice(product.getSalePrice())
                .payoutRate(MarketPolicy.PRODUCT_PAYOUT_RATE)
                .build();
    }
}