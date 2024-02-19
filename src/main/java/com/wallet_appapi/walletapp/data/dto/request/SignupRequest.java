package com.wallet_appapi.walletapp.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequest {
    private String userName;
    private String password;
}
