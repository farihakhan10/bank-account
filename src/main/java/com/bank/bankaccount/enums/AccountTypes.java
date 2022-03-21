package com.bank.bankaccount.enums;

import lombok.Getter;

@Getter
public enum AccountTypes {
    CURRENT_ACCOUNT("current"),
    SAVING_ACCOUNT("saving");

    private final String value;
    AccountTypes(String value) {
        this.value = value;
    }
}
