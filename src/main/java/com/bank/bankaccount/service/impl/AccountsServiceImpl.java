package com.bank.bankaccount.service.impl;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.enums.AccountTypes;
import com.bank.bankaccount.service.AccountsService;
import com.bank.bankaccount.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private CustomerValidator customerValidator;

    @Override
    public AccountDTO createAccount(Long customerID, AccountTypes accountType, BigDecimal initBalance) {
        CustomerDTO customer = customerValidator.validateCustomerId(customerID);

        return null;
    }
}
