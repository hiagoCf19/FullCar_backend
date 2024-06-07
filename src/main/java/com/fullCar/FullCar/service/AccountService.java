package com.fullCar.FullCar.service;

import com.fullCar.FullCar.dto.RegisterAccountDTO;
import com.fullCar.FullCar.exception.AccountNotFound;
import com.fullCar.FullCar.model.Account;
import com.fullCar.FullCar.repository.AccountRepository;
import com.fullCar.FullCar.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createuser(RegisterAccountDTO data){
        Account createdAccount = new Account( );
        String password= PasswordUtil.encoder(data.password_hash());
        createdAccount.setUsername(data.username());
        createdAccount.setEmail(data.email());
        createdAccount.setPassword_hash(password);
        createdAccount.setCreated_at(data.created_at());
        accountRepository.save(createdAccount);
        return createdAccount;
    }
    public Account verifyAccountExist(Long id){
        return accountRepository.findById(id).orElseThrow(()-> new AccountNotFound("É necessário ter uma conta para anunciar!") );
    }

}
