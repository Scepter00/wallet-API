package com.wallet_appapi.walletapp.service;

import com.wallet_appapi.walletapp.data.dto.request.CreateTransactionRequest;
import com.wallet_appapi.walletapp.data.dto.response.CreateTransactionResponse;
import com.wallet_appapi.walletapp.data.models.TransactionHistory;

import java.io.IOException;
import java.util.List;

public interface TransactionService {

    CreateTransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest) throws IOException;

    List<TransactionHistory> viewAll();
}