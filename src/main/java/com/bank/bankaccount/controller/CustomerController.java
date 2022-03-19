package com.bank.bankaccount.controller;

import com.bank.bankaccount.dto.ApiResponse;
import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.exception.NotFoundException;
import com.bank.bankaccount.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired private CustomerService customerService;

    @GetMapping("/customer/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> getCustomerDetailsByCustomerId (
            @PathVariable Long id) throws NotFoundException {

        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return ResponseEntity.ok(new ApiResponse<>(null, customerDTO));
    }
}
