package com.bank.bankaccount.service.impl;

import com.bank.bankaccount.Constant;
import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.enums.AccountTypes;
import com.bank.bankaccount.enums.Error;
import com.bank.bankaccount.enums.Status;
import com.bank.bankaccount.exception.BankAccountCustomException;
import com.bank.bankaccount.exception.NotFoundException;
import com.bank.bankaccount.model.Account;
import com.bank.bankaccount.model.Customer;
import com.bank.bankaccount.repository.AccountsRepository;
import com.bank.bankaccount.service.AccountsService;
import com.bank.bankaccount.service.CustomerService;
import com.bank.bankaccount.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired private CustomerService customerService;
    @Autowired private AccountsRepository accountsRepository;
    @Autowired private TransactionService transactionService;

    @Override
    public AccountDTO createAccount(Long customerID, AccountTypes accountType, Double initBalance) throws BankAccountCustomException {
        CustomerDTO customer = customerService.getCustomerById(customerID);

        // check if current account already exists then throw error
        Optional<List<Account>> accounts = getCustomerAccounts(customerID);
        if(accounts.isPresent() && getAccount(accounts.get(), accountType).isPresent()) {
            throw new BankAccountCustomException(
                    Error.ALREADY_EXISTS.getCode(), String.format(Error.ALREADY_EXISTS.getMsg(), "Current account"));
        }

        // create and save account
        Account account = prepareAccountEntity(customer.toEntity(), accountType);
        account = accountsRepository.save(account);

        // get account no
        Long accountNo = accountsRepository.getAccountNoById(account.getId());
        account.setAccountNo(accountNo);

        if(initBalance>0)
            performInitialTransaction(account, initBalance);

        return account.toDTO();
    }

    private void performInitialTransaction(Account account, Double initBalance) {
        // fetch GL account
        Optional<Account> gl = accountsRepository.findByAccountNo(Constant.DEFAULT_GL);
        if(gl.isPresent()) {
            Account glAccount = gl.get();
            // GL to wallet funds transfer
            transactionService.performGLToCustomerTransfer(glAccount, account, initBalance);
        }
    }

    private Optional<List<Account>> getCustomerAccounts(Long customerId) {
        return accountsRepository.findByCustomerID(customerId);
    }

    private Optional<Account> getAccount(List<Account> accounts, AccountTypes accountType) {
         return accounts.stream()
                 .filter(account -> account.getAccountType().equals(accountType.getValue()))
                 .findFirst();
    }

    private Account prepareAccountEntity(Customer customer, AccountTypes accountType) {
        String title = customer.getFirstName() + " " + customer.getLastName();
        return new Account().toBuilder().balance(0.0).accountType(accountType.getValue())
                .currency(Constant.DEFAULT_CURRENCY).status(Status.ACTIVE.name()).customer(customer).title(title)
                .isActive('Y').build();
    }

    private Optional<Account> getAccount(Long accountNo) {
        return accountsRepository.findByAccountNo(accountNo);
    }

    @Override
    public boolean accountExists(AccountDTO account) {
        return getAccount(account.getAccountNo()).isPresent();
    }

    @Override
    public Double checkBalance(AccountDTO account) {
        return accountsRepository.getAccountBalance(account.getAccountNo());
    }

    @Override
    public AccountDTO getAccountDetails(Long accountNo) throws BankAccountCustomException {
        Optional<Account> account = getAccount(accountNo);
        if(account.isEmpty())
            throw new NotFoundException(Error.NOT_FOUND.getCode(), String.format(Error.NOT_FOUND.getMsg(), "Account"));
        return account.get().toDTO();
    }
}
