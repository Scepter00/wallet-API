package com.wallet_appapi.walletapp.data.repositories;

import com.wallet_appapi.walletapp.data.models.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionHistory, Long> {
    List<TransactionHistory> findByWalletId(Long walletId);
}
