package com.back.boundedcontext.payout.app;

import com.back.boundedcontext.payout.domain.Payout;
import com.back.boundedcontext.payout.domain.PayoutMember;
import com.back.boundedcontext.payout.out.PayoutMemberRepository;
import com.back.boundedcontext.payout.out.PayoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutCreatePayoutUseCase {
    private final PayoutRepository payoutRepository;
    private final PayoutMemberRepository payoutMemberRepository;

    public Payout createPayout(int payeeId) {
        PayoutMember _payee = payoutMemberRepository.getReferenceById(payeeId);
        Payout payout = payoutRepository.save(
                new Payout(_payee)
        );
        return payout;
    }
}