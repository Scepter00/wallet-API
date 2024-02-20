package com.wallet_appapi.walletapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet_appapi.walletapp.data.dto.request.CreateTransactionRequest;
import com.wallet_appapi.walletapp.data.dto.response.CreateTransactionResponse;
import com.wallet_appapi.walletapp.data.models.TransactionHistory;
import com.wallet_appapi.walletapp.data.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImp implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CreateTransactionResponse createTransaction(CreateTransactionRequest createTransactionRequest) throws IOException {
        try {
            String url = "https://fsi.ng/api/v1/fcmb/payments/b2b/transfers";

            String json = convertObjToJson(createTransactionRequest);

            Response response = sendRequest(url, "POST", json);

            CreateTransactionResponse createTransactionResponse = handleApiResponse(response);

            if(response.isSuccessful()){
                TransactionHistory transactionHistory  = new TransactionHistory();
                saveTransaction(transactionHistory,createTransactionRequest);
            }

            response.close();
            return createTransactionResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveTransaction(TransactionHistory transactionHistory, CreateTransactionRequest createTransactionRequest) {
        transactionHistory.setOriginatorAccountName(createTransactionRequest.getOriginatorAccountName());
        transactionHistory.setOriginatorAccountNumber(createTransactionRequest.getOriginatorAccountNumber());
        transactionHistory.setBeneficiaryAccountName(createTransactionRequest.getBeneficiaryAccountName());
        transactionHistory.setBeneficiaryAccountNumber(createTransactionRequest.getBeneficiaryAccountNumber());
        transactionHistory.setAmount(Double.valueOf(createTransactionRequest.getAmount()));
        transactionRepository.save(transactionHistory);
    }

    private CreateTransactionResponse handleApiResponse(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        assert responseBody != null;
        return objectMapper.readValue(responseBody.string(), CreateTransactionResponse.class);
    }


    private String convertObjToJson(CreateTransactionRequest createTransactionRequest) throws JsonProcessingException {

        return objectMapper.writeValueAsString(createTransactionRequest);
    }

    private Response sendRequest(String url, String post, String json) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url(url)
                .method(post, requestBody)
                .addHeader("Sandbox-Key", "c7AuCjA7Jm1mN7hvhc5ZwrHmvDsrXkLj1708204297")
                .addHeader("x-ibm-client-id", "f")
                .build();
        return okHttpClient.newCall(request).execute();


    }
}