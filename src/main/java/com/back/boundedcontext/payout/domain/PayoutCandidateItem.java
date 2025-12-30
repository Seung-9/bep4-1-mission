package com.back.boundedcontext.payout.domain;

import static jakarta.persistence.FetchType.LAZY;

import com.back.global.jpa.entity.BaseAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAYOUT_PAYOUT_CANDIDATE_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class PayoutCandidateItem extends BaseAndTime {
    @Enumerated(EnumType.STRING)
    private PayoutEventType eventType;
    String relTypeCode;
    private int relId;
    private LocalDateTime paymentDate;
    @ManyToOne(fetch = LAZY)
    private PayoutMember payer;
    @ManyToOne(fetch = LAZY)
    private PayoutMember payee;
    private long amount;
    @OneToOne(fetch = LAZY)
    private PayoutItem payoutItem;

    public static PayoutCandidateItem create(PayoutEventType eventType, String relTypeCode, int relId, LocalDateTime paymentDate, PayoutMember payer, PayoutMember payee, long amount) {
        return PayoutCandidateItem.builder()
                .eventType(eventType)
                .relTypeCode(relTypeCode)
                .relId(relId)
                .paymentDate(paymentDate)
                .payer(payer)
                .payee(payee)
                .amount(amount)
                .build();
    }

    public void addPayoutItem(PayoutItem payoutItem) {
        this.payoutItem = payoutItem;
    }
}