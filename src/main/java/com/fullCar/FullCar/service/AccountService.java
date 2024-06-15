package com.fullCar.FullCar.service;

import com.fullCar.FullCar.dto.AccountRequestDTO;
import com.fullCar.FullCar.dto.AccountResponseDTO;
import com.fullCar.FullCar.exception.AccountAlreadyExistException;
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

    public Account createAccount(AccountRequestDTO data){
        var verifyAccountAlreadyExist= accountRepository.getAccountByEmail(data.email());
        if(verifyAccountAlreadyExist.isPresent()){
            throw new AccountAlreadyExistException("Account already exists");
        }
        Account createdAccount = new Account();
        String password= PasswordUtil.encoder(data.password());
        createdAccount.setEmail(data.email());
        createdAccount.setUser_name(data.user_name());
        createdAccount.setPassword(password);
        createdAccount.setCreated_at(LocalDateTime.now());
        accountRepository.save(createdAccount);
        return createdAccount;
    }
    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElseThrow(()-> new AccountNotFound("You need an account to ad!") );
    }
    public AccountResponseDTO getAccount(Long id){
        var account= getAccountById(id);
        return new AccountResponseDTO(account);
    }
}
