package com.back.boundedcontext.cash.app;

import com.back.boundedcontext.cash.domain.CashMember;
import com.back.boundedcontext.cash.domain.Wallet;
import com.back.shared.cash.dto.CashMemberDto;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.dto.PayoutDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CashFacade {
    private final CashSyncMemberUseCase cashSyncMemberUseCase;
    private final CashCreateWalletUseCase cashCreateWalletUseCase;
    private final CashCompleteOrderPaymentUseCase cashCompleteOrderPaymentUseCase;
    private final CashCompletePayoutUseCase cashCompletePayoutUseCase;
    private final CashSupport cashSupport;

    @Transactional
    public CashMember syncMember(MemberDto member) {
        return cashSyncMemberUseCase.syncMember(member);
    }

    @Transactional
    public Wallet createWallet(CashMemberDto holder) {
        return cashCreateWalletUseCase.createWallet(holder);
    }

    public Optional<CashMember> findMemberByUsername(String username) {
        return cashSupport.findMemberByUsername(username);
    }

    public Optional<Wallet> findWalletByHolder(CashMember holder) {
        return cashSupport.findWalletByHolder(holder);
    }

    @Transactional
    public void handle(OrderDto order, long pgPaymentAmount) {
        cashCompleteOrderPaymentUseCase.completeOrderPayment(order, pgPaymentAmount);
    }

    public Optional<Wallet> findWalletByHolderId(int holderId) {
        return cashSupport.findWalletByHolderId(holderId);
    }

    @Transactional
    public void completePayout(PayoutDto payout) {
        cashCompletePayoutUseCase.completePayout(payout);
    }
}
