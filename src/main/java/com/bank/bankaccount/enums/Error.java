package com.bank.bankaccount.enums;

public enum Error {

    ARG_REQUIRED(1, "%s required"),
    NOT_FOUND(2, "%s not found"),
    INVALID_REQUEST(3, "Invalid Request data"),
    ALREADY_EXISTS(4, "%s already exists"),
    INVALID(5, "Invalid %s"),
    LOW_BALANCE(6, "Not sufficient balance")
    ;
    private final int code;
    private final String msg;

    Error(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
