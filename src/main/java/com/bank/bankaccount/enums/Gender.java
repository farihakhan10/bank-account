package com.bank.bankaccount.enums;

public enum Gender {
    MALE('M'),
    FEMALE('F'),
    OTHER('O');

    private char value;
    Gender(char value) {
        this.value = value;
    }
}
