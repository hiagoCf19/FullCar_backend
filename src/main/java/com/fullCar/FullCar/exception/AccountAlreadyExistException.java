package com.fullCar.FullCar.exception;

public class AccountAlreadyExistException extends RuntimeException{
    public AccountAlreadyExistException(String message){
        super(message);
    }
}
