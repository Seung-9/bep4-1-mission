package com.back.boundedcontext.cash.out;

import com.back.boundedcontext.cash.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}
