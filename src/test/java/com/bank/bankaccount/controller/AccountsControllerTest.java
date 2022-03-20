package com.bank.bankaccount.controller;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.enums.AccountTypes;
import com.bank.bankaccount.enums.Currency;
import com.bank.bankaccount.enums.Error;
import com.bank.bankaccount.enums.Status;
import com.bank.bankaccount.exception.BankAccountCustomException;
import com.bank.bankaccount.service.AccountsService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountsController.class)
class AccountsControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private AccountsService accountsService;

    @Test
    void createCurrentAccountByCustomerId() throws Exception {
        AccountDTO accountDTO = getAccountDTOMock();
        when(accountsService.createAccount(anyLong(), any(), anyDouble()))
                .thenReturn(accountDTO);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("customerID", "1");
        requestParams.add("initialCredit", "10");

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/accounts/current")
                .params(requestParams)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void testCreateCurrentAccountByCustomerId_accountAlreadyExists() throws Exception {
        when(accountsService.createAccount(anyLong(), any(), anyDouble()))
                .thenThrow(new BankAccountCustomException(
                        Error.ALREADY_EXISTS.getCode(), String.format(Error.ALREADY_EXISTS.getMsg(), "Current account")));

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("customerID", "1");
        requestParams.add("initialCredit", "10");

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/accounts/current")
                .params(requestParams)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isInternalServerError())
                .andReturn();

    }

    private AccountDTO getAccountDTOMock() {
        return new AccountDTO().toBuilder().accountNo(123456789L).accountType(AccountTypes.CURRENT_ACCOUNT.getValue())
                .status(Status.ACTIVE.name()).isActive(true).title("Tom Clarke").currency(Currency.USD.getCode())
                .balance(100.00).transactions(null).build();
    }

}