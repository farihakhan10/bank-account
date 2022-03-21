package com.bank.bankaccount.service.impl;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.enums.AccountTypes;
import com.bank.bankaccount.enums.Status;
import com.bank.bankaccount.model.Account;
import com.bank.bankaccount.model.Customer;
import com.bank.bankaccount.repository.AccountsRepository;
import com.bank.bankaccount.service.AccountsService;
import com.bank.bankaccount.service.CustomerService;
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
class AccountsServiceImplTest {

    @InjectMocks
    private AccountsService accountsService = new AccountsServiceImpl();
    @Mock
    private CustomerService customerService = new CustomerServiceImpl();
    @Mock
    private AccountsRepository accountsRepository;
    @Mock
    private TransactionService transactionService = new TransactionServiceImpl();

    @Test
    void createAccount() {

        Optional<Account> mockAccount = getMockAccount();
        Optional<List<Account>> accountList = Optional.of(List.of());
        CustomerDTO mockCustomerDTO = getMockCustomerDTO();

        when(customerService.getCustomerById(anyLong())).thenReturn(mockCustomerDTO);
        when(accountsRepository.findByAccountNo(anyLong())).thenReturn(mockAccount);
        when(accountsRepository.getAccountNoById(anyLong())).thenReturn(2L);
        when(accountsRepository.save(any())).thenReturn(getMockAccount().get());
        when(accountsRepository.findByCustomerID(anyLong())).thenReturn(accountList);

        when(transactionService.performGLToCustomerTransfer(any(), any(), anyDouble(), anyString())).thenReturn(23L);

        AccountDTO accountDTO = accountsService.createAccount(2L, AccountTypes.CURRENT_ACCOUNT, 5.0);

        assertNotNull(accountDTO);
        assertEquals("John Mike", accountDTO.getTitle());
        assertEquals(Status.ACTIVE.name(), accountDTO.getStatus());
    }

    private Optional<Account> getMockAccount() {
        return Optional.ofNullable(new Account().toBuilder().accountNo(201111112L).id(2L).accountType(AccountTypes.CURRENT_ACCOUNT.getValue())
                .isActive('Y').balance(20.00).currency(Constant.DEFAULT_CURRENCY).title("John Mike").status(Status.ACTIVE.name())
                .customer(new Customer().toBuilder().build()).build());
    }

    private AccountDTO getMockAccountDTO() {
        return new AccountDTO().toBuilder().accountNo(201111112L).id(2L).accountType(AccountTypes.CURRENT_ACCOUNT.getValue())
                .isActive(true).balance(20.00).currency(Constant.DEFAULT_CURRENCY).title("John Mike").status(Status.ACTIVE.name())
                .customer(new CustomerDTO().toBuilder().id(1L).build()).build();
    }

    private CustomerDTO getMockCustomerDTO() {
        return new CustomerDTO().toBuilder().firstName("John").lastName("Mike").gender("M")
                .accounts(List.of(new AccountDTO().toBuilder().accountNo(123456L).balance(5.0).isActive(true).build()))
                .status(Status.ACTIVE.name()).mobileNo("123456789")
                .build();
    }

    @Test
    void accountExists() {

        Optional<Account> mockAccount = getMockAccount();
        when(accountsRepository.findByAccountNo(anyLong())).thenReturn(mockAccount);
        boolean isExist = accountsService.accountExists(getMockAccountDTO());
        assertTrue(isExist);
    }

    @Test
    void checkBalance() {
        when(accountsRepository.getAccountBalance(anyLong())).thenReturn(10.0);
        Double balance = accountsService.checkBalance(getMockAccountDTO());
        assertNotNull(balance);
        assertEquals(10.0, balance);
    }
}
