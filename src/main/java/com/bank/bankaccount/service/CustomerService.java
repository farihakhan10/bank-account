package com.bank.bankaccount.service;

import com.bank.bankaccount.model.Customer;

public interface CustomerService {
    Customer getCustomerById(Long customerId);
}
