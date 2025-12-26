package com.back.boundedcontext.member.in;

import com.back.boundedcontext.member.app.MemberFacade;
import com.back.boundedcontext.member.domain.Member;
import com.back.shared.member.dto.MemberCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class MemberDataInit {
    private final MemberDataInit self;
    private final MemberFacade memberFacade;

    public MemberDataInit(
            @Lazy MemberDataInit self,
            MemberFacade memberFacade
    ) {
        this.self = self;
        this.memberFacade = memberFacade;
    }

    @Bean
    @Order(1)
    public ApplicationRunner memberDataInitApplicationRunner() {
        return args -> {
            self.makeBaseMembers();
        };
    }

    @Transactional
    public void makeBaseMembers() {
        if (memberFacade.count() > 0) return;

        Member systemMember = memberFacade.join(new MemberCreateRequest("system", "1234", "시스템")).getData();
        Member holdingMember = memberFacade.join(new MemberCreateRequest("holding", "1234", "홀딩")).getData();
        Member adminMember = memberFacade.join(new MemberCreateRequest("admin", "1234", "관리자")).getData();
        Member user1Member = memberFacade.join(new MemberCreateRequest("user1", "1234", "유저1")).getData();
        Member user2Member = memberFacade.join(new MemberCreateRequest("user2", "1234", "유저2")).getData();
        Member user3Member = memberFacade.join(new MemberCreateRequest("user3", "1234", "유저3")).getData();
    }
}