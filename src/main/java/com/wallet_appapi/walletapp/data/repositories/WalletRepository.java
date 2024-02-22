package com.wallet_appapi.walletapp.data.repositories;

import com.wallet_appapi.walletapp.data.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByUserId(Long userId);
}
