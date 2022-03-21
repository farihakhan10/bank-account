package com.bank.bankaccount.service.impl;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.enums.Status;
import com.bank.bankaccount.enums.Error;
import com.bank.bankaccount.exception.NotFoundException;
import com.bank.bankaccount.model.Account;
import com.bank.bankaccount.model.Customer;
import com.bank.bankaccount.repository.CustomerRepository;
import com.bank.bankaccount.service.CustomerService;
import com.bank.bankaccount.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private TransactionService transactionService = new TransactionServiceImpl();

    @Test
    void getCustomerById() {

        Optional<Customer> mockCustomer = getMockCustomer();
        CustomerDTO mockCustomerDTO = getMockCustomerDTO();

        when(customerRepository.findById(anyLong())).thenReturn(mockCustomer);
        when(customerService.getCustomerById(anyLong())).thenReturn(mockCustomerDTO);
        when(transactionService.getCustomerTransactions(any())).thenReturn(mockCustomerDTO);

        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        assertNotNull(customerDTO);
        assertEquals("John", customerDTO.getFirstName());
        assertEquals(Status.ACTIVE.name(), customerDTO.getStatus());
        assertNotNull(customerDTO.getAccounts());
        assertEquals(1, customerDTO.getAccounts().size());
        assertEquals(5.0, customerDTO.getAccounts().stream().findFirst().get().getBalance());
    }

    @Test
    void getCustomerById_NotFound_throwsException() {

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () ->
                customerService.getCustomerById(1L), String.format(Error.NOT_FOUND.getMsg(), "Customer"));
        assertNotNull(exception);
        Assertions.assertEquals(String.format(Error.NOT_FOUND.getMsg(), "Customer"), exception.getMsg());
    }

    @Test
    void getCustomerById_throwsValidationException() {

        ValidationException exception = Assertions.assertThrows(ValidationException.class, () ->
                customerService.getCustomerById(null), String.format(Error.ARG_REQUIRED.getMsg(), "Customer"));
        assertNotNull(exception);
        assertEquals(String.format(Error.ARG_REQUIRED.getMsg(), "customerId"), exception.getMessage());
    }

    private CustomerDTO getMockCustomerDTO() {
        return new CustomerDTO().toBuilder().firstName("John").lastName("Mike").gender("M")
                .accounts(List.of(new AccountDTO().toBuilder().accountNo(123456L).balance(5.0).isActive(true).build()))
                .status(Status.ACTIVE.name()).mobileNo("123456789")
                .build();
    }

    private Optional<Customer> getMockCustomer() {
        return Optional.ofNullable(new Customer().toBuilder()
                .firstName("John").lastName("Mike").gender("M").status(Status.ACTIVE.name()).mobileNo("123456789")
                .accounts(List.of(new Account().toBuilder().accountNo(123456L).balance(5.0).isActive('Y').build()))
                .build());
    }
}
