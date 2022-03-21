package com.bank.bankaccount.dto;

import com.bank.bankaccount.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    @JsonIgnore
    private Long id;
    private String accountType;
    private boolean isActive;
    private String title;
    private Long accountNo;
    private String status;
    @JsonIgnore
    private CustomerDTO customer;
    private List<TransactionDTO> transactions;
    private String currency;
    private Double balance;

    public Account toEntity() {
        return new Account().toBuilder().id(this.id).accountType(this.accountType).accountNo(this.accountNo)
                .isActive(this.isActive ? 'Y' : 'N').title(this.title).customer(this.customer.toEntity())
                .status(this.status).currency(this.currency).balance(this.balance)
                .build();
    }
}
