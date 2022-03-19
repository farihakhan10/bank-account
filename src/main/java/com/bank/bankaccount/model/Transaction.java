package com.bank.bankaccount.model;

import com.bank.bankaccount.dto.TransactionDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Builder(toBuilder = true)
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "txn_type")
    private String txnType;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "currency")
    private String currency;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    private Account fromAccount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    private Account toAccount;

    public TransactionDTO toDTO() {
        return new TransactionDTO().toBuilder().id(this.id).status(this.status).transactionType(this.txnType)
                .amount(this.amount).currency(this.currency).fromAccountNo(fromAccount.getAccountNo())
                .toAccountNo(this.toAccount.getAccountNo()).build();
    }
}
