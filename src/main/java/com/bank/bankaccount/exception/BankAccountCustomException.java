package com.bank.bankaccount.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BankAccountCustomException extends RuntimeException implements Serializable {

    private int code;
    private String msg;

}
