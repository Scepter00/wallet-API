package com.wallet_appapi.walletapp.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
    private String nameEnquiryRef;
    private String destinationInstitutionCode;
    private String channelCode;
    private String beneficiaryAccountNumber;
    private String beneficiaryAccountName;
    private String beneficiaryBankVerificationNumber;
    private String beneficiaryKYCLevel;
    private String originatorAccountName;
    private String originatorAccountNumber;
    private String transactionNarration;
    private String paymentReference;
    private String amount;
    private String traceId;
    private String chargeAmount;
    private String platformType;
}
