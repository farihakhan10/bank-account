package com.bank.bankaccount.service;

import com.bank.bankaccount.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO getCustomerById(Long customerId);
}
