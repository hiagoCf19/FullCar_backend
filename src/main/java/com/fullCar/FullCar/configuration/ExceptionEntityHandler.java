package com.fullCar.FullCar.configuration;

import com.fullCar.FullCar.dto.BeanValidationErrorDTO;
import com.fullCar.FullCar.dto.ErrorDTO;
import com.fullCar.FullCar.exception.AccountNotFound;
import com.fullCar.FullCar.exception.AdsNotFound;
import com.fullCar.FullCar.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    @ExceptionHandler({AuthenticationException.class, NoHandlerFoundException.class})
    public ResponseEntity<ErrorDTO> handleAuthentication(AuthenticationException e){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDTO(e.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequest(MethodArgumentNotValidException exception){
        var errors= exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(BeanValidationErrorDTO::new));
    }


}
