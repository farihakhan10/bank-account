package com.bank.bankaccount.dto;

import java.util.List;

public class ApiResponse<T> {

    private List<ErrorInfo> errors;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(List<ErrorInfo> errors, T data) {
        this.errors = errors;
        this.data = data;
    }

    public List<ErrorInfo> getErrors() {
        return errors;
    }

    public T getData() {
        return data;
    }

}
