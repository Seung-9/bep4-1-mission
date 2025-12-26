package com.back.boundedcontext.member.app;

import com.back.boundedcontext.member.domain.Member;
import com.back.global.rsdata.RsData;
import com.back.shared.member.dto.MemberCreateRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberFacade {
    private final MemberSupport memberSupport;
    private final MemberUseCase memberUseCase;
    private final MemberGetRandomSecureTipUseCase memberGetRandomSecureTipUseCase;

    public long count() {
        return memberSupport.count();
    }

    @Transactional
    public RsData<Member> join(MemberCreateRequest request) {
        return memberUseCase.join(request.getUserName(), request.getPassword(), request.getNickName());
    }

    public Optional<Member> findByUsername(String username) {
        return memberSupport.findByUsername(username);
    }

    public Optional<Member> findById(int id) {
        return memberSupport.findById(id);
    }

    public String getRandomSecureTip() {
        return "비밀번호의 유효기간은 %d일 입니다.".formatted(memberGetRandomSecureTipUseCase.getRandomSecureTip());
    }
}
