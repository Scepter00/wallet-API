package com.wallet_appapi.walletapp.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class FcmbApiService {
    private static final String BASE_URL = "https://fsi-core-dev.inits.dev/api/v1/fcmb";
    private static final String CLIENT_ID = "f";
    private static final String SANDBOX_KEY = "c7AuCjA7Jm1mN7hvhc5ZwrHmvDsrXkLj1708204297";
    private final RestTemplate restTemplate;

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-ibm-client-id", CLIENT_ID);
        headers.set("Sandbox-Key", SANDBOX_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }

    public FcmbApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> makeB2BTransfer(String requestBody) {
        String url = BASE_URL + "/payments/b2b/transfers";
        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        return restTemplate.postForEntity(url, entity, String.class);
    }

    public ResponseEntity<String> makeB2BInternalTransfer(String requestBody) {
        String url = BASE_URL + "/payments/b2b/internal/transfers";
        HttpHeaders headers = createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        return restTemplate.postForEntity(url, entity, String.class);
    }
}
