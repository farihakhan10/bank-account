package com.bank.bankaccount.service;

import com.bank.bankaccount.model.Account;

public interface TransactionService {
    Long performGLToCustomerTransfer(Account fromAccount, Account toAccount, Double amount);
}
