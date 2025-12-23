package com.back.boundedcontext.member.service;

import com.back.boundedcontext.member.dto.request.MemberCreateRequest;
import com.back.boundedcontext.member.entity.Member;
import com.back.boundedcontext.member.repository.MemberRepository;
import com.back.global.exception.DomainException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public long count() {
        return memberRepository.count();
    }

    public Member join(MemberCreateRequest request) {
        findByUsername(request.getUsername()).ifPresent(m -> {
            throw new DomainException("409-1", "이미 존재하는 username 입니다.");
        });
        Member member = Member.create(request.getUsername(), request.getPassword(), request.getPassword());
        return memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findById(int id) {
        return memberRepository.findById(id);
    }
}