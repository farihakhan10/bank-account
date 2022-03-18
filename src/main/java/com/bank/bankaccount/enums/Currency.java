package com.bank.bankaccount.enums;

import lombok.Getter;

@Getter
public enum Currency {

    USD("USD", "US Dollar"),
    GBP("GBP", "British Pound Sterling"),
    CAD("CAD", "Canadian Dollar"),
    EUR("EUR", "Euro")
    ;

    private final String code;
    private final String description;

    Currency(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
