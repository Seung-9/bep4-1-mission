package com.back.boundedcontext.cash.in;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.back.boundedcontext.cash.app.CashFacade;
import com.back.boundedcontext.cash.domain.CashMember;
import com.back.shared.member.event.MemberCreateEvent;
import com.back.shared.member.event.MemberModifiedEvent;
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
        CashMember member = cashFacade.syncMember(event.getMember());
        cashFacade.createWallet(member);
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberModifiedEvent event) {
        cashFacade.syncMember(event.getMember());
    }
}
