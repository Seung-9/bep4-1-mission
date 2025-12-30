package com.back.shared.member.dto;

import com.back.boundedcontext.member.domain.Member;
import java.time.LocalDateTime;

public record MemberDto (
    int id,
    LocalDateTime createDate,
    LocalDateTime modifyDate,
    String username,
    String nickname,
    int activityScore
) {
    public static MemberDto of(Member member) {
        return new MemberDto(
                member.getId(),
                member.getCreateDate(),
                member.getModifyDate(),
                member.getUsername(),
                member.getNickname(),
                member.getActivityScore()
        );
    }
}
