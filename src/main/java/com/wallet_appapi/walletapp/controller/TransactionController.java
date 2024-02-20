package com.wallet_appapi.walletapp.controller;

import com.wallet_appapi.walletapp.data.dto.request.CreateTransactionRequest;
import com.wallet_appapi.walletapp.data.dto.response.CreateTransactionResponse;
import com.wallet_appapi.walletapp.data.models.TransactionHistory;
import com.wallet_appapi.walletapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/transaction")
    public ResponseEntity<CreateTransactionResponse> createTransaction(@RequestBody CreateTransactionRequest createTransactionRequest) throws IOException {
        return ResponseEntity.ok(transactionService.createTransaction(createTransactionRequest));
    }

    @GetMapping("/viewAllTransactions")
    public ResponseEntity<List<TransactionHistory>> viewAllTransactions() {
        return ResponseEntity.ok(transactionService.viewAll());
    }
}
