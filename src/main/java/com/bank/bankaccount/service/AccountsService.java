package com.bank.bankaccount.service;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.enums.AccountTypes;


public interface AccountsService {

    AccountDTO createAccount(Long customerId, AccountTypes accountType, Double initBalance);
    boolean accountExists(AccountDTO account);
    Double checkBalance(AccountDTO account);
    AccountDTO getAccountDetails(Long accountNo);
}
