package com.back.shared.cash.dto;

import com.back.boundedcontext.cash.domain.Wallet;
import java.time.LocalDateTime;

public record WalletDto(
        int id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        int holderId,
        String holderName,
        long balance
) {
    public static WalletDto of(Wallet wallet) {
        return new WalletDto(wallet.getId(), wallet.getCreateDate(),
                wallet.getModifyDate(), wallet.getHolder().getId(),
                wallet.getHolder().getUsername(), wallet.getBalance()
        );
    }
}
