package com.back.boundedcontext.market.out;

import com.back.boundedcontext.market.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
