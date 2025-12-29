package com.back.boundedcontext.market.app;

import com.back.boundedcontext.market.domain.Order;
import com.back.boundedcontext.market.out.OrderRepository;
import com.back.shared.cash.event.CashOrderPaymentSucceededEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketCompleteOrderPaymentUseCase {
    private final OrderRepository orderRepository;

    public void handle(CashOrderPaymentSucceededEvent event) {
        Order order = orderRepository.findById(event.orderDto().id()).get();

        order.completePayment();
    }
}