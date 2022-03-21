package com.bank.bankaccount.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE('M'),
    FEMALE('F'),
    OTHER('O');

    private final char value;
    Gender(char value) {
        this.value = value;
    }
}
