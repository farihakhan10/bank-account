package com.bank.bankaccount.service;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.enums.AccountTypes;

import java.math.BigDecimal;

public interface AccountsService {

    AccountDTO createAccount(Long customerId, AccountTypes accountType, BigDecimal initBalance);

}
