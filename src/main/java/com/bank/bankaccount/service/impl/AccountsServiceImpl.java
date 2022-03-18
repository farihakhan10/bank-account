package com.bank.bankaccount.service.impl;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.enums.AccountTypes;
import com.bank.bankaccount.service.AccountsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountsServiceImpl implements AccountsService {
    @Override
    public AccountDTO createAccount(CustomerDTO customer, AccountTypes accountType, BigDecimal initBalance) {
        return null;
    }
}
