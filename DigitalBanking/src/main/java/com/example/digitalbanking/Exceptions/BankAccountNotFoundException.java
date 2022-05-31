package com.example.digitalbanking.Exceptions;

public class BankAccountNotFoundException extends Throwable {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
