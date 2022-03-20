package com.bank.bankaccount.controller;

import com.bank.bankaccount.dto.CustomerDTO;
import com.bank.bankaccount.enums.Error;
import com.bank.bankaccount.exception.NotFoundException;
import com.bank.bankaccount.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;

    @Test
    void getCustomerDetailsByCustomerId() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO().toBuilder().build();
        when(customerService.getCustomerById(anyLong()))
                .thenReturn(customerDTO);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/customer/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getCustomerDetailsByCustomerId_invalidIdThrowException() throws Exception {

        when(customerService.getCustomerById(anyLong()))
                .thenThrow(new NotFoundException(Error.NOT_FOUND.getCode(), String.format(Error.NOT_FOUND.getMsg(), "Customer")));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/v1/customer/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andReturn();
    }
}