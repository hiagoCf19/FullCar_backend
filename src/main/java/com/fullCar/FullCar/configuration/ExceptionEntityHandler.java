package com.fullCar.FullCar.configuration;

import com.fullCar.FullCar.dto.ErrorDTO;
import com.fullCar.FullCar.exception.AccountNotFound;
import com.fullCar.FullCar.exception.AdsNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<ErrorDTO> handleAccountNotFound(AccountNotFound e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
    }
    @ExceptionHandler(AdsNotFound.class)
    public ResponseEntity<ErrorDTO> handleAdsNotFound(AdsNotFound e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
    }


}
