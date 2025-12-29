package com.back.boundedcontext.market.app;

import com.back.boundedcontext.market.domain.Cart;
import com.back.boundedcontext.market.domain.MarketMember;
import com.back.boundedcontext.market.out.CartRepository;
import com.back.boundedcontext.market.out.MarketMemberRepository;
import com.back.global.rsdata.RsData;
import com.back.shared.market.dto.MarketMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCreateCartUseCase {
    private final MarketMemberRepository marketMemberRepository;
    private final CartRepository cartRepository;

    public RsData<Cart> createCart(MarketMemberDto buyer) {
        MarketMember _buyer = marketMemberRepository.getReferenceById(buyer.id());

        Cart cart = new Cart(_buyer);

        cartRepository.save(cart);

        return new RsData<>(
                "201-1",
                "장바구니가 생성되었습니다.",
                cart
        );
    }
}