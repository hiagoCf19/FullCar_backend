package com.fullCar.FullCar.controller;
import com.fullCar.FullCar.dto.AccountIdDTO;
import com.fullCar.FullCar.dto.AccountRequestDTO;
import com.fullCar.FullCar.dto.AccountResponseDTO;
import com.fullCar.FullCar.service.AccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<AccountIdDTO> createAccount(@RequestBody @Valid AccountRequestDTO data, UriComponentsBuilder uriComponentsBuilder){
        var account= accountService.createAccount(data);
        var uri= uriComponentsBuilder.path("/account/{id}").buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(uri).body(new AccountIdDTO(account.getId()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable Long id){
        var account= accountService.getAccount(id);
        return ResponseEntity.ok().body(account);
    }
}
