package com.back.shared.cash.dto;


import com.back.boundedcontext.cash.domain.CashMember;
import java.time.LocalDateTime;

public record CashMemberDto(
    int id,
    LocalDateTime createDate,
    LocalDateTime modifyDate,
    String username,
    String nickname,
    int activityScore
) {
    public static CashMemberDto of(CashMember cashMember) {
        return new CashMemberDto(
                cashMember.getId(),
                cashMember.getCreateDate(),
                cashMember.getModifyDate(),
                cashMember.getUsername(),
                cashMember.getNickname(),
                cashMember.getActivityScore()
        );
    }
}