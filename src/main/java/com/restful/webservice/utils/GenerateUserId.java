package com.restful.webservice.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class GenerateUserId {
    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generateId(int lengt){
        return generateRandomString(lengt);
    }
    private String generateRandomString(int lengt){
        StringBuilder returnValue = new StringBuilder(lengt);

        for (int i =0; i<lengt; i++){
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
}
