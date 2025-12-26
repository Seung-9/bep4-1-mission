package com.back.boundedcontext.cash.out;

import com.back.boundedcontext.cash.domain.CashMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashMemberRepository extends JpaRepository<CashMember, Integer> {
    Optional<CashMember> findByUsername(String username);
}