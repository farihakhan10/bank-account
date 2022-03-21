package com.bank.bankaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {

    private int code;
    private String message;

    public ErrorInfo(int code) {
        this.code = code;
    }

}
