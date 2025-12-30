package com.back.boundedcontext.payout.app;


import com.back.boundedcontext.payout.domain.PayoutCandidateItem;
import com.back.boundedcontext.payout.domain.PayoutMember;
import com.back.boundedcontext.payout.out.PayoutCandidateItemRepository;
import com.back.boundedcontext.payout.out.PayoutMemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutSupport {
    private final PayoutMemberRepository payoutMemberRepository;
    private final PayoutCandidateItemRepository payoutCandidateItemRepository;

    public Optional<PayoutMember> findSystemMember() {
        return payoutMemberRepository.findByUsername("system");
    }

    public Optional<PayoutMember> findHolingMember() {
        return payoutMemberRepository.findByUsername("holding");
    }

    public Optional<PayoutMember> findMemberById(int id) {
        return payoutMemberRepository.findById(id);
    }

    public List<PayoutCandidateItem> findPayoutCandidateItems() {
        return payoutCandidateItemRepository.findAll();
    }
}