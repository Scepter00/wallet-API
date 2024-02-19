package com.wallet_appapi.walletapp.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String amount;
    private LocalDateTime transactionDateTime;
    private String beneficiaryAccountNumber;
    private String beneficiaryAccountName;
    private String originatorAccountName;
    private String originatorAccountNumber;
    @ManyToOne
    private Wallet wallet;
}
