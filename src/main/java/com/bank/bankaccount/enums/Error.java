package com.bank.bankaccount.enums;

public enum Error {

    ARG_REQUIRED(1, "% required"),
    NOT_FOUND(2, "% not found"),
    INVALID_REQUEST(3, "Invalid Request data")
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
