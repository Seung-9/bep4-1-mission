package com.back.boundedcontext.market.app;

import com.back.boundedcontext.market.domain.MarketMember;
import com.back.boundedcontext.market.domain.Product;
import com.back.boundedcontext.market.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCreateProductUseCase {
    private final ProductRepository productRepository;

    public Product createProduct(MarketMember seller, String sourceTypeCode, int sourceId,
            String name, String description, long price, long salePrice) {
        Product product = Product.create(seller, sourceTypeCode, sourceId, name, description, price, salePrice);
        return productRepository.save(product);
    }
}