package com.fullCar.FullCar.repository;

import com.fullCar.FullCar.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long > {
    UserDetails findByEmail(String email);
    Optional<Account> getAccountByEmail(String email);
}
