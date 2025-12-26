package com.back.boundedcontext.cash.out;

import com.back.boundedcontext.cash.domain.CashMember;
import com.back.boundedcontext.cash.domain.Wallet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Optional<Wallet> findByHolder(CashMember holder);
}
