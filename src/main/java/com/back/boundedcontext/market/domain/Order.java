package com.back.boundedcontext.market.domain;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

import com.back.global.jpa.entity.BaseAndTime;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.market.event.MarketOrderPaymentRequestedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARKET_ORDER")
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Order extends BaseAndTime {
    @ManyToOne(fetch = LAZY)
    private MarketMember buyer;
    private long price;
    private long salePrice;
    private LocalDateTime requestPaymentDate;
    private LocalDateTime paymentDate;

    @OneToMany(mappedBy = "order", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public static Order create(Cart cart) {
        Order order = new Order();
        order.buyer = cart.getBuyer();
        cart.getItems().forEach(item -> {
            order.addItem(item.getProduct());
        });
        return order;
    }

    public void addItem(Product product) {
        OrderItem orderItem = OrderItem.create(this, product);
        items.add(orderItem);
        price += product.getPrice();
        salePrice += product.getSalePrice();
    }

    public void completePayment() {
        paymentDate = LocalDateTime.now();
    }

    public boolean isPaid() {
        return paymentDate != null;
    }

    public void requestPayment(long pgPaymentAmount) {
        requestPaymentDate = LocalDateTime.now();
        publishEvent(
                new MarketOrderPaymentRequestedEvent(
                        OrderDto.of(this),
                        pgPaymentAmount
                )
        );
    }

    public void cancelRequestPayment() {
        requestPaymentDate = null;
    }
}