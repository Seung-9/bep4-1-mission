package com.back.shared.payout.dto;

import com.back.standard.modeltype.HasModelTypeCode;
import java.time.LocalDateTime;

public record PayoutDto (
        int id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        int payeeId,
        String payeeName,
        LocalDateTime payoutDate,
        long amount,
        boolean payeeSystem
) implements HasModelTypeCode {
    @Override
    public String getModelTypeCode() {
        return "Payout";
    }
}
