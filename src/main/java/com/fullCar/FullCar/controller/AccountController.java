package com.fullCar.FullCar.controller;

import com.fullCar.FullCar.dto.RegisterAccountDTO;
import com.fullCar.FullCar.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/create")
    @Transactional
    public ResponseEntity createUser(@RequestBody RegisterAccountDTO data){
        accountService.createuser(data);
        return ResponseEntity.ok().build();
    }
}
