package com.bank.bankaccount.service.impl;

import com.bank.bankaccount.Constant;
import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.enums.AccountTypes;
import com.bank.bankaccount.enums.Error;
import com.bank.bankaccount.enums.Status;
import com.bank.bankaccount.exception.BankAccountCustomException;
import com.bank.bankaccount.model.Account;
import com.bank.bankaccount.model.Customer;
import com.bank.bankaccount.repository.AccountsRepository;
import com.bank.bankaccount.service.AccountsService;
import com.bank.bankaccount.validator.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired private CustomerValidator customerValidator;
    @Autowired private AccountsRepository accountsRepository;

    @Override
    public AccountDTO createAccount(Long customerID, AccountTypes accountType, Double initBalance) throws BankAccountCustomException {
        Customer customer = customerValidator.validateCustomerId(customerID);

        // check if current account already exists then throw error
        Optional<List<Account>> accounts = getCustomerAccounts(customerID);
        if(accounts.isPresent() && getAccount(accounts.get(), accountType).isPresent()) {
            throw new BankAccountCustomException(
                    Error.ALREADY_EXISTS.getCode(), String.format(Error.ALREADY_EXISTS.getMsg(), "Current account"));
        }

        // create and save account
        Account account = prepareAccountEntity(customer, accountType);
        accountsRepository.save(account);

        if(initBalance>0) {
            // TODO GL to wallet funds transfer

        }
        return account.toDTO();
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
}
