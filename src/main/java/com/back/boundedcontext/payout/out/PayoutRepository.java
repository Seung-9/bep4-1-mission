package com.back.boundedcontext.payout.out;


import com.back.boundedcontext.payout.domain.Payout;
import com.back.boundedcontext.payout.domain.PayoutMember;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepository extends JpaRepository<Payout, Integer> {
    Optional<Payout> findByPayeeAndPayoutDateIsNull(PayoutMember payee);
    List<Payout> findByPayoutDateIsNullAndAmountGreaterThanOrderByIdAsc(long amount, Pageable pageable);
}