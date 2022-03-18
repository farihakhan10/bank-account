package com.bank.bankaccount.controller;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.dto.ApiResponse;
import com.bank.bankaccount.enums.AccountTypes;
import com.bank.bankaccount.exception.BankAccountCustomException;
import com.bank.bankaccount.service.AccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AccountsController {

    @Autowired private AccountsService accountsService;

    @PostMapping("/accounts/current")
    public ResponseEntity<ApiResponse<AccountDTO>> createCurrentAccountByCustomerId (
            @RequestParam("customerID") @NotNull Long customerID, @RequestParam("initialCredit") BigDecimal initialCredit) throws BankAccountCustomException {

        return ResponseEntity.ok(new ApiResponse<>(null,
                accountsService.createAccount(customerID, AccountTypes.CURRENT_ACCOUNT, initialCredit)));
    }
}
