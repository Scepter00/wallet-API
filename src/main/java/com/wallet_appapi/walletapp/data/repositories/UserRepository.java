package com.wallet_appapi.walletapp.data.repositories;

import com.wallet_appapi.walletapp.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}