package com.fullCar.FullCar.controller;

import com.fullCar.FullCar.dto.AccountTokenDTO;
import com.fullCar.FullCar.dto.AuthenticationRequestDTO;
import com.fullCar.FullCar.model.Account;
import com.fullCar.FullCar.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO authData){
        var usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(authData.email(), authData.password());
        var auth= manager.authenticate(usernamePasswordAuthenticationToken);
        var tokenJWT= tokenService.generateToken((Account) auth.getPrincipal());
        return ResponseEntity.ok(new AccountTokenDTO(tokenJWT));
    }
}
