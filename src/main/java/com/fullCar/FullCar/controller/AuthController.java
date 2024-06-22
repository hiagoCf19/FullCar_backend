package com.fullCar.FullCar.controller;

import com.fullCar.FullCar.dto.AuthenticadedDTO;
import com.fullCar.FullCar.dto.AuthenticationRequestDTO;
import com.fullCar.FullCar.model.Account;
import com.fullCar.FullCar.service.AccountService;
import com.fullCar.FullCar.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AuthenticadedDTO> login(@RequestBody @Valid AuthenticationRequestDTO authData){
        var usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(authData.email(), authData.password());
        var auth= manager.authenticate(usernamePasswordAuthenticationToken);
        var tokenJWT= tokenService.generateToken((Account) auth.getPrincipal());
        return ResponseEntity.ok(new AuthenticadedDTO(accountService.getAccountByEmail(authData.email()), tokenJWT));
    }
}
