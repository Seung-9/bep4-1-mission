package com.back.boundedcontext.payout.out;

import com.back.boundedcontext.payout.domain.PayoutMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutMemberRepository extends JpaRepository<PayoutMember, Integer> {
    Optional<PayoutMember> findByUsername(String username);
}