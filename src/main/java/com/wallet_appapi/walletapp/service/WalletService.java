package com.wallet_appapi.walletapp.service;

import com.wallet_appapi.walletapp.data.models.User;
import com.wallet_appapi.walletapp.data.models.Wallet;
import com.wallet_appapi.walletapp.data.repositories.UserRepository;
import com.wallet_appapi.walletapp.data.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet createWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    public List<User> getAllUsersWithWallets() {
        return userRepository.findAll();
    }
}
