package com.fullCar.FullCar.service;

import com.fullCar.FullCar.dto.AccountRequestDTO;
import com.fullCar.FullCar.dto.AccountResponseDTO;
import com.fullCar.FullCar.exception.AccountNotFound;
import com.fullCar.FullCar.model.Account;
import com.fullCar.FullCar.repository.AccountRepository;
import com.fullCar.FullCar.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account createUser(AccountRequestDTO data){
        Account createdAccount = new Account();
        String password= PasswordUtil.encoder(data.password_hash());
        createdAccount.setEmail(data.email());
        createdAccount.setUser_name(data.user_name());
        createdAccount.setPassword_hash(password);
        createdAccount.setCreated_at(LocalDateTime.now());
        accountRepository.save(createdAccount);
        return createdAccount;
    }
    public Account verifyAccountExist(Long id){
        return accountRepository.findById(id).orElseThrow(()-> new AccountNotFound("You need an account to ad!") );
    }
    public AccountResponseDTO getAccount(Long id){
        var account= verifyAccountExist(id);
        return new AccountResponseDTO(account);
    }
}
