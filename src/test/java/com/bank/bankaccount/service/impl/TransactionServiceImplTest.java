package com.bank.bankaccount.service.impl;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.enums.AccountTypes;
import com.bank.bankaccount.enums.Status;
import com.bank.bankaccount.enums.TransactionStatus;
import com.bank.bankaccount.enums.TransactionType;
import com.bank.bankaccount.model.Account;
import com.bank.bankaccount.model.Customer;
import com.bank.bankaccount.model.Transaction;
import com.bank.bankaccount.repository.AccountsRepository;
import com.bank.bankaccount.repository.TransactionRepository;
import com.bank.bankaccount.service.TransactionService;
import com.bank.bankaccount.util.Constant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    TransactionService transactionService = new TransactionServiceImpl();
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    AccountsRepository accountsRepository;

    @Test
    void performGLToCustomerTransfer() {
        Optional<Account> mockAccount = getMockAccount();
        when(accountsRepository.getAccountBalance(anyLong())).thenReturn(15.0);
        when(accountsRepository.findById(anyLong())).thenReturn(mockAccount);
        when(transactionRepository.save(any())).thenReturn(getMockTransaction());

        Long txnId = transactionService.performGLToCustomerTransfer(mockAccount.get(), mockAccount.get(), 10.0, " ");
        assertNotNull(txnId);
    }

    private Optional<Account> getMockAccount() {
        return Optional.ofNullable(new Account().toBuilder().accountNo(201111112L).id(2L).accountType(AccountTypes.CURRENT_ACCOUNT.getValue())
                .isActive('Y').balance(20.00).currency(Constant.DEFAULT_CURRENCY).title("John Mike").status(Status.ACTIVE.name())
                .customer(new Customer().toBuilder().build()).build());
    }

    private Transaction getMockTransaction() {
        return new Transaction().toBuilder().id(2L).amount(5.0)
                .currency(Constant.DEFAULT_CURRENCY).txnType(TransactionType.GL2C.name()).status(TransactionStatus.COMPLETED.name())
                .txnDetail("txnDetail").build();
    }

    private CustomerDTO getMockCustomerDTO() {
        return new CustomerDTO().toBuilder().firstName("John").lastName("Mike").gender("M")
                .accounts(List.of(new AccountDTO().toBuilder().accountNo(123456L).balance(5.0).isActive(true).build()))
                .status(Status.ACTIVE.name()).mobileNo("123456789")
                .build();
    }
}
