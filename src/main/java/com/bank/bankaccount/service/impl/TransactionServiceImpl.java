package com.bank.bankaccount.service.impl;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.dto.TransactionDTO;
import com.bank.bankaccount.enums.Error;
import com.bank.bankaccount.enums.TransactionStatus;
import com.bank.bankaccount.enums.TransactionType;
import com.bank.bankaccount.exception.BankAccountCustomException;
import com.bank.bankaccount.model.Account;
import com.bank.bankaccount.model.Transaction;
import com.bank.bankaccount.repository.AccountsRepository;
import com.bank.bankaccount.repository.TransactionRepository;
import com.bank.bankaccount.service.AccountsService;
import com.bank.bankaccount.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired TransactionRepository transactionRepository;
    @Autowired AccountsService accountsService;
    @Autowired AccountsRepository accountsRepository;

    @Override
    @Transactional
    public Long performGLToCustomerTransfer(Account fromAccount, Account toAccount, Double amount, String txnDetail) {

        // validate txn
        validateTransaction(fromAccount, toAccount, amount);
        // prepare debit txn
        Transaction txn = prepareTransaction(fromAccount, toAccount, amount, TransactionType.GL2C, txnDetail);
        // debit account balance deduct in case it is customer account
        // credit balance to payee
        creditBalance(toAccount, amount);
        // save txn
        txn = transactionRepository.save(txn);

        return txn.getId();
    }

    @Override
    public CustomerDTO getCustomerTransactions(CustomerDTO customer) {

        for (AccountDTO acc :
                customer.getAccounts()) {
            List<TransactionDTO> txns = new ArrayList<>(getAccountTransactions(acc.getId()));
            acc.setTransactions(txns);
        }
        return customer;
    }

    private List<TransactionDTO> getAccountTransactions(Long accountId) {

        // get all transactions
        List<Transaction> txns = transactionRepository.getAccountTransactions(accountId);
        return txns.stream().map(Transaction::toDTO).collect(Collectors.toList());
    }

    private void creditBalance(Account account, Double amount) {
        Double currBalance = account.getBalance();
        account.setBalance(currBalance + amount);
        accountsRepository.save(account);
    }

    private void validateTransaction(Account fromAccount, Account toAccount, Double amount) {

        // validate debit account
        validateAccount(fromAccount, "debit account");
        // validate credit account
        validateAccount(toAccount, "credit account");
        // validate debit account balance

        Double debitBalance = accountsRepository.getAccountBalance(fromAccount.getAccountNo());
        if(debitBalance < amount)
            throw new BankAccountCustomException(Error.LOW_BALANCE.getCode(), Error.LOW_BALANCE.getMsg());

    }

    private void validateAccount(Account account, String errorMag) {
        if(!accountsService.accountExists(account.toDTO()))
            throw new BankAccountCustomException(Error.INVALID.getCode(), String.format(Error.INVALID.getMsg(), errorMag));

    }

    private Transaction prepareTransaction(Account fromAccount, Account toAccount, Double amount,
                                           TransactionType type, String txnDetail) {

        return new Transaction().toBuilder().fromAccount(fromAccount).toAccount(toAccount).amount(amount)
                .currency(toAccount.getCurrency()).txnType(type.name()).status(TransactionStatus.COMPLETED.name())
                .txnDetail(txnDetail).build();
    }
}
