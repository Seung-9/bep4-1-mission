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
@Table(name = "MARKET_PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class Product extends BaseAndTime {
    @ManyToOne(fetch = LAZY)
    private MarketMember seller;
    private String sourceTypeCode;
    private int sourceId;
    private String name;
    private String description;
    private long price;
    private long salePrice;

    public static Product create(MarketMember seller, String sourceTypeCode, int sourceId,
                                 String name, String description, long price, long salePrice) {
        return Product.builder()
                .seller(seller)
                .sourceTypeCode(sourceTypeCode)
                .sourceId(sourceId)
                .name(name)
                .description(description)
                .price(price)
                .salePrice(salePrice)
                .build();
    }
}