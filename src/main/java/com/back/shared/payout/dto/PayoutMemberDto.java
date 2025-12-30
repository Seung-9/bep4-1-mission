package com.back.shared.payout.dto;

import com.back.boundedcontext.payout.domain.PayoutMember;
import java.time.LocalDateTime;

public record PayoutMemberDto(
        int id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String username,
        String nickname,
        int activityScore
) {
    public static PayoutMemberDto of(PayoutMember payoutMember) {
        return new PayoutMemberDto(
                payoutMember.getId(),
                payoutMember.getCreateDate(),
                payoutMember.getModifyDate(),
                payoutMember.getUsername(),
                payoutMember.getNickname(),
                payoutMember.getActivityScore()
        );
    }
}
