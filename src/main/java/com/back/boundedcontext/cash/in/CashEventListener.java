package com.back.boundedcontext.cash.in;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.back.boundedcontext.cash.app.CashFacade;
import com.back.shared.cash.event.CashMemberCreatedEvent;
import com.back.shared.market.event.MarketOrderPaymentRequestedEvent;
import com.back.shared.member.event.MemberCreateEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import com.back.shared.payout.event.PayoutCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class CashEventListener {
    private final CashFacade cashFacade;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberCreateEvent event) {
        cashFacade.syncMember(event.getMember());
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberModifiedEvent event) {
        cashFacade.syncMember(event.getMember());
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(CashMemberCreatedEvent event) {
        cashFacade.createWallet(event.getMember());
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MarketOrderPaymentRequestedEvent event) {
        cashFacade.handle(event.order(), event.pgPaymentAmount());
    }

    @TransactionalEventListener
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PayoutCompletedEvent event) {
        cashFacade.completePayout(event.payout());
    }
}
