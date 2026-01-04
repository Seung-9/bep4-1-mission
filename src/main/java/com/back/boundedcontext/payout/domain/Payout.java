package com.back.boundedcontext.payout.domain;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

import com.back.global.jpa.entity.BaseAndTime;
import com.back.shared.payout.dto.PayoutDto;
import com.back.shared.payout.event.PayoutCompletedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PAYOUT_PAYOUT")
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Payout extends BaseAndTime {
    @ManyToOne(fetch = LAZY)
    private PayoutMember payee;
    @Setter
    private LocalDateTime payoutDate;
    private long amount;

    @OneToMany(mappedBy = "payout", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<PayoutItem> items = new ArrayList<>();

    public Payout(PayoutMember payee) {
        this.payee = payee;
    }

    public PayoutItem addItem(PayoutEventType eventType, String relTypeCode, int relId, LocalDateTime payDate, PayoutMember payer, PayoutMember payee, long amount) {
        PayoutItem payoutItem = PayoutItem.create(
                this, eventType, relTypeCode, relId, payDate, payer, payee, amount
        );
        items.add(payoutItem);
        this.amount += amount;
        return payoutItem;
    }

    public void completePayout() {
        this.payoutDate = LocalDateTime.now();
        publishEvent(
                new PayoutCompletedEvent(
                        toDto()
                )
        );
    }

    public PayoutDto toDto() {
        return new PayoutDto(
                getId(),
                getCreateDate(),
                getModifyDate(),
                payee.getId(),
                payee.getNickname(),
                payoutDate,
                amount,
                payee.isSystem()
        );
    }
}