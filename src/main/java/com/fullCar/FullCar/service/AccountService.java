package com.fullCar.FullCar.service;

import com.fullCar.FullCar.dto.RegisterAccountDTO;
import com.fullCar.FullCar.model.Account;
import com.fullCar.FullCar.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createuser(RegisterAccountDTO data){
        Account createdAccount = new Account( );
        createdAccount.setUsername(data.username());
        createdAccount.setEmail(data.email());
        createdAccount.setPassword_hash(data.password_hash());
        createdAccount.setCreated_at(data.created_at());
        accountRepository.save(createdAccount);
        return createdAccount;
    }

}
