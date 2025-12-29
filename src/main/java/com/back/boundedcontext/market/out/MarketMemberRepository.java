package com.back.boundedcontext.market.out;

import com.back.boundedcontext.market.domain.MarketMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketMemberRepository extends JpaRepository<MarketMember, Integer> {
    Optional<MarketMember> findByUsername(String username);
}