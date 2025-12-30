package com.back.boundedcontext.payout.app;

import com.back.boundedcontext.payout.domain.PayoutMember;
import com.back.boundedcontext.payout.out.PayoutMemberRepository;
import com.back.global.event.EventPublisher;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.dto.PayoutMemberDto;
import com.back.shared.payout.event.PayoutMemberCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutSyncMemberUseCase {
    private final PayoutMemberRepository payoutMemberRepository;
    private final EventPublisher eventPublisher;

    public PayoutMember syncMember(MemberDto member) {
        boolean isNew = !payoutMemberRepository.existsById(member.id());
        PayoutMember _member = payoutMemberRepository.save(
                new PayoutMember(
                        member.id(),
                        member.createDate(),
                        member.modifyDate(),
                        member.username(),
                        "",
                        member.nickname(),
                        member.activityScore()
                )
        );
        if (isNew) {
            eventPublisher.publishEvent(
                    new PayoutMemberCreatedEvent(
                            PayoutMemberDto.of(_member)
                    )
            );
        }
        return _member;
    }
}