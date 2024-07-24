package com.fullCar.FullCar.service;

import com.fullCar.FullCar.dto.AccountRequestDTO;
import com.fullCar.FullCar.dto.AccountUpdateRequestDTO;
import com.fullCar.FullCar.dto.AccountResponseDTO;
import com.fullCar.FullCar.exception.AccountAlreadyExistException;
import com.fullCar.FullCar.exception.AccountNotFound;
import com.fullCar.FullCar.model.Account;
import com.fullCar.FullCar.repository.AccountRepository;
import com.fullCar.FullCar.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;


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
        createdAccount.setIs_confirmed(false);
        accountRepository.save(createdAccount);
        triggerConfirmationEmail(data.email(), createdAccount);
        return createdAccount;
    }
    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElseThrow(()-> new AccountNotFound("Account not found") );
    }
    public AccountResponseDTO getAccount(Long id){
        var account= getAccountById(id);
        return new AccountResponseDTO(account);
    }
    public AccountResponseDTO getAccountByEmail(String email){
        var account= accountRepository.getAccountByEmail(email).orElseThrow(()-> new AccountNotFound("account not found"));
        return new AccountResponseDTO(account);
    }
    public void updateAccountData(AccountUpdateRequestDTO data){
        var account= getAccountById(data.id());
        if(data.email() != null){
            account.setEmail(data.email());
        }
        if(data.user_name() != null){
            account.setUser_name(data.user_name());
        }

    }
    private void triggerConfirmationEmail(String AccountEmail, Account account){
          try{
              SimpleMailMessage mail= new SimpleMailMessage();
              mail.setFrom(sender);
              mail.setTo(AccountEmail);
              mail.setSubject("Confirmaçao de conta");
              mail.setText(template(account));
              javaMailSender.send(mail);
          }catch (Exception e){
              System.out.println("erro ao enviar o email: " + e.getLocalizedMessage());
          }
    }
    private String template(Account account){
        String baseUrl = "http://localhost:8080/account/";
        String endpoint = "/confirm";
        String url= baseUrl+ account.getId() + endpoint;
        return String.format("""
                 
                 Olá %s,
                 
                 Bem-vindo à FullCar!
                 
                 Para concluir a criação da sua conta, por favor, confirme seu endereço de e-mail clicando no link abaixo:
                 
                 %s
                 
                 Se você não criou uma conta na FullCar, ignore este e-mail.
                 
                 Agradecemos por escolher a FullCar!
                 
                 Atenciosamente,
                 Equipe Fullcar
         
                """, account.getUser_name(), url);
    }


}
