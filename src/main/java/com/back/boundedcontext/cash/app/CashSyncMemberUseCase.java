package com.back.boundedcontext.cash.app;

import com.back.boundedcontext.cash.domain.CashMember;
import com.back.boundedcontext.cash.out.CashMemberRepository;
import com.back.global.event.EventPublisher;
import com.back.shared.cash.dto.CashMemberDto;
import com.back.shared.cash.event.CashMemberCreatedEvent;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashSyncMemberUseCase {
    private final CashMemberRepository cashMemberRepository;
    private final EventPublisher eventPublisher;

    public CashMember syncMember(MemberDto member) {
        boolean isNew = !cashMemberRepository.existsById(member.id());

        CashMember _member = cashMemberRepository.save(
                new CashMember(
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
                    new CashMemberCreatedEvent(
                            CashMemberDto.of(_member)
                    )
            );
        }

        return _member;
    }
}
