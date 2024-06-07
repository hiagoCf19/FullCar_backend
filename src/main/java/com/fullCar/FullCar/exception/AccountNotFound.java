package com.fullCar.FullCar.exception;

import org.springframework.beans.factory.annotation.Autowired;

public class AccountNotFound extends RuntimeException{
    public AccountNotFound(String message){
        super(message);
    }
}
