package com.bank.bankaccount.dto;

import com.bank.bankaccount.model.Customer;
import com.bank.bankaccount.model.Transaction;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private String accountType;
    private Boolean isActive;
    private String title;
    private Long accountNo;
    private String status;
    private Customer customer;
    private List<Transaction> txns;
    private String currency;
    private Double balance;
}
