package com.back.boundedcontext.payout.out;


import com.back.boundedcontext.payout.domain.Payout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepository extends JpaRepository<Payout, Integer> {
}