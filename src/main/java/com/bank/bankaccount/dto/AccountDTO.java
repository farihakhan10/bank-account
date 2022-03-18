package com.bank.bankaccount.dto;

import com.bank.bankaccount.model.Customer;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private String accountType;
    private Character isActive;
    private String title;
    private String iBan;
    private String accountNo;
    private String status;
    private Customer customer;
    private String currency;
    private BigDecimal balance;
}
