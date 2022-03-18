package com.bank.bankaccount.dto;

import lombok.*;


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
    private String accountNo;
    private String status;
    private Long customerId;
    private String currency;
    private Double balance;
}
