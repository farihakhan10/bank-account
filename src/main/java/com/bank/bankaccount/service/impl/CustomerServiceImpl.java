package com.bank.bankaccount.service.impl;

import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.enums.Error;
import com.bank.bankaccount.exception.NotFoundException;
import com.bank.bankaccount.model.Customer;
import com.bank.bankaccount.repository.CustomerRepository;
import com.bank.bankaccount.service.CustomerService;
import com.bank.bankaccount.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionService transactionService;

    @Override
    public CustomerDTO getCustomerById(Long customerId) {

        validateCustomerId(customerId);

        CustomerDTO customerDTO = getCustomer(customerId);
        // get all customer transactions
        transactionService.getCustomerTransactions(customerDTO);

        log.info("Success get Customer by id:{}", customerId);
        return customerDTO;
    }

    private CustomerDTO getCustomer(Long customerId) {
        log.info("Validate Customer id:{}", customerId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isEmpty())
            throw new NotFoundException(Error.NOT_FOUND.getCode(), String.format(Error.NOT_FOUND.getMsg(), "Customer"));

        return customer.get().toDTO();
    }

    private void validateCustomerId(Long customerId) {
        log.info("Validating Customer by id:{}", customerId);
        if(customerId == null){
            throw new ValidationException(String.format(Error.ARG_REQUIRED.getMsg(), "customerId"));
        }
    }

}
