package com.fullCar.FullCar.configuration;

import com.fullCar.FullCar.dto.BeanValidationErrorDTO;
import com.fullCar.FullCar.dto.ErrorDTO;
import com.fullCar.FullCar.exception.AccountAlreadyExistException;
import com.fullCar.FullCar.exception.AccountNotFound;
import com.fullCar.FullCar.exception.AdsNotFound;
import com.fullCar.FullCar.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

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
    public ResponseEntity<Object> handleBadRequest(MethodArgumentNotValidException exception) {
        // Mapeando os erros de campo para uma lista de erros mais informativa
        var errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new BeanValidationErrorDTO(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        // Retornando os erros no corpo da resposta com status 400
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<ErrorDTO> handleAccountAlreadyExist (AccountAlreadyExistException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
}


}
