package com.example.minifacebookclone.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String s) {
        System.out.println("No name");
    }
}
