package com.back.boundedcontext.member.app;

import com.back.boundedcontext.member.domain.Member;
import com.back.boundedcontext.member.out.MemberRepository;
import com.back.global.event.EventPublisher;
import com.back.global.exception.DomainException;
import com.back.global.rsdata.RsData;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.member.event.MemberCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUseCase {
    private final MemberRepository memberRepository;
    private final EventPublisher eventPublisher;

    public RsData<Member> join(String username, String password, String nickname) {
        memberRepository.findByUsername(username).ifPresent(m -> {
            throw new DomainException("409-1", "이미 존재하는 username 입니다.");
        });
        Member member = new Member(username, password, nickname);
        Member saveMember = memberRepository.save(member);
        eventPublisher.publishEvent(new MemberCreateEvent(new MemberDto(saveMember)));

        return new RsData<>("201-1", "%d번 회원이 생성되었습니다."
                .formatted(saveMember.getId()), saveMember);
    }
}
