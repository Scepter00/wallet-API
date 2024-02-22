package com.wallet_appapi.walletapp.service;

import com.wallet_appapi.walletapp.data.dto.request.LoginRequest;
import com.wallet_appapi.walletapp.data.dto.request.SignupRequest;
import com.wallet_appapi.walletapp.data.dto.response.LoginResponse;
import com.wallet_appapi.walletapp.data.dto.response.SignupResponse;
import com.wallet_appapi.walletapp.data.models.User;
import com.wallet_appapi.walletapp.data.models.Wallet;
import com.wallet_appapi.walletapp.data.repositories.UserRepository;
import com.wallet_appapi.walletapp.data.repositories.WalletRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    public SignupResponse createUser(SignupRequest signupRequest) {
        if (userRepository.findByUserName(signupRequest.getUserName()) != null) {
            throw new RuntimeException("Username already exists");
        }
        String hashedPassword = BCrypt.hashpw(signupRequest.getPassword(), BCrypt.gensalt());
        User user = new User();
        user.setPassword(hashedPassword);
        user.setUserName(signupRequest.getUserName());
        User savedUser = userRepository.save(user);
        createWallet(savedUser);
        return createSignupResponse(savedUser);
    }

    private SignupResponse createSignupResponse(User user) {
        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setId(user.getId());
        return signupResponse;
    }

    private LoginResponse createLoginResponse(User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(user.getId());
        return loginResponse;
    }

    private void createWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        walletRepository.save(wallet);
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUserName());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!BCrypt.checkpw(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return createLoginResponse(user);
    }
}
