package com.bank.bankaccount.exception;

import com.bank.bankaccount.dto.ErrorInfo;
import com.bank.bankaccount.enums.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvisor {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorInfo handleValidationException(ValidationException exception) {
        log.error("Validation exception occurred with errors: {}", exception.getMessage());
        return new ErrorInfo(Error.INVALID_REQUEST.getCode(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BankAccountCustomException.class)
    public ErrorInfo handleCustomException(BankAccountCustomException exception) {
        log.error("Custom exception occurred with errors: {}", exception.getMsg());
        return new ErrorInfo(exception.getCode(), exception.getMessage());
    }
}
