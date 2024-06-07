package com.fullCar.FullCar.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    public static String encoder(String password){
        BCryptPasswordEncoder passwordEncoder=  new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}