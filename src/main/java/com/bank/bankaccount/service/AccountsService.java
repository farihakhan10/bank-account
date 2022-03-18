package com.bank.bankaccount.service;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.enums.AccountTypes;

import java.math.BigDecimal;

public interface AccountsService {

    AccountDTO createAccount(CustomerDTO customer, AccountTypes accountType, BigDecimal initBalance);

}
