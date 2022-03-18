package com.bank.bankaccount.validator;

import com.bank.bankaccount.enums.Error;
import com.bank.bankaccount.exception.BankAccountCustomException;
import com.bank.bankaccount.model.Customer;
import com.bank.bankaccount.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;

@Slf4j
@Component
public class CustomerValidator {

    @Autowired private CustomerService customerService;

    @Transactional(readOnly = true)
    public Customer validateCustomerId(Long customerId) throws BankAccountCustomException {

        log.info("Start validating Customer id:{}", customerId);
        if(customerId == null){
            throw new ValidationException(String.format(Error.ARG_REQUIRED.getMsg(), "customerId"));
        }

        Customer customer = customerService.getCustomerById(customerId);
        if(customer == null){
            log.error("Customer does not exist");
            throw new BankAccountCustomException(Error.NOT_FOUND.getCode(), String.format(Error.NOT_FOUND.getMsg(), "Customer"));
        }

        log.info("Success validating Customer id:{}", customerId);
        return customer;
    }
}
