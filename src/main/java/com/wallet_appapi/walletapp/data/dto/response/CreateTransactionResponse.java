package com.wallet_appapi.walletapp.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionResponse {

    public String message;
    public Data data;
    public ResponseData responseData;
    public String statusCode;
    public String description;
    public boolean status;
}
