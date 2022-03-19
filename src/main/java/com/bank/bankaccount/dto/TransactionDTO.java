package com.bank.bankaccount.dto;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private String transactionType;
    private Double amount;
    private String status;
    private String currency;
    private Long fromAccountNo;
    private Long toAccountNo;
}
