package com.bank.bankaccount.service.impl;

import com.bank.bankaccount.enums.Error;
import com.bank.bankaccount.enums.TransactionStatus;
import com.bank.bankaccount.enums.TransactionType;
import com.bank.bankaccount.exception.BankAccountCustomException;
import com.bank.bankaccount.model.Account;
import com.bank.bankaccount.model.Transaction;
import com.bank.bankaccount.repository.TransactionRepository;
import com.bank.bankaccount.service.AccountsService;
import com.bank.bankaccount.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired TransactionRepository transactionRepository;
    @Autowired AccountsService accountsService;

    @Override
    @Transactional
    public Long performGLToCustomerTransfer(Account fromAccount, Account toAccount, Double amount) {
//        Long txnId = null;

        // validate txn
        validateTransaction(fromAccount, toAccount, amount);
        // prepare debit txn
        Transaction txn = prepareTransaction(fromAccount, toAccount, amount, TransactionType.GL2C);
        // debit account balance deduct in case it is customer account
        // save txn
        txn = transactionRepository.save(txn);
        return txn.getId();
    }

    private boolean validateTransaction(Account fromAccount, Account toAccount, Double amount) {

        // validate debit account
        validateAccount(fromAccount, "debit account");
        // validate credit account
        validateAccount(toAccount, "credit account");
        // validate debit account balance
        Double debitBalance = accountsService.checkBalance(fromAccount);
        if(debitBalance < amount)
            throw new BankAccountCustomException(Error.LOW_BALANCE.getCode(), Error.LOW_BALANCE.getMsg());

        return true;
    }

    private void validateAccount(Account account, String errorMag) {
        if(!accountsService.accountExists(account))
            throw new BankAccountCustomException(Error.INVALID.getCode(), String.format(Error.INVALID.getMsg(), errorMag));

    }

    private Transaction prepareTransaction(Account fromAccount, Account toAccount, Double amount, TransactionType type) {

        return new Transaction().toBuilder().fromAccount(fromAccount).toAccount(toAccount).amount(amount)
                .currency(toAccount.getCurrency()).txnType(type.name()).status(TransactionStatus.COMPLETED.name()).build();
    }
}
