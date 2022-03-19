package com.bank.bankaccount.service;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.enums.AccountTypes;
import com.bank.bankaccount.model.Account;


public interface AccountsService {

    AccountDTO createAccount(Long customerId, AccountTypes accountType, Double initBalance);
    boolean accountExists(Account account);
    Double checkBalance(Account account);
}
