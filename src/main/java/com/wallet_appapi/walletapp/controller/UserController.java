package com.wallet_appapi.walletapp.controller;

import com.wallet_appapi.walletapp.data.dto.request.LoginRequest;
import com.wallet_appapi.walletapp.data.dto.request.SignupRequest;
import com.wallet_appapi.walletapp.data.dto.response.LoginResponse;
import com.wallet_appapi.walletapp.data.dto.response.SignupResponse;
import com.wallet_appapi.walletapp.data.models.User;
import com.wallet_appapi.walletapp.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImp userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> createUser(@RequestBody SignupRequest signupRequest) {
        SignupResponse createdUser = userService.createUser(signupRequest);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        LoginResponse loggedInUser = userService.loginUser(loginRequest);
        return ResponseEntity.ok(loggedInUser);
    }

}
