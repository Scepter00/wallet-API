package com.wallet_appapi.walletapp.service;

import com.wallet_appapi.walletapp.data.models.User;

public interface UserService {
    User createUser(User user);

    User loginUser(String username, String password);
}
