package com.bank.bankaccount.model;

import com.bank.bankaccount.dto.AccountDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountType;

    @Column(name = "is_active")
    private Character isActive;

    @Column(name = "title")
    private String title;

    @GeneratedValue
    private Long accountNo;

    @NotNull
    @Size(max = 20)
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    private Customer customer;

    @OneToMany(mappedBy = "toAccount")
    private List<Transaction> payeeTxns;

    @OneToMany(mappedBy = "fromAccount")
    private List<Transaction> payerTxns;

    @NotNull
    @Column(name="currency")
    private String currency;

    @NotNull
    @Column(name="balance")
    private Double balance;

    public AccountDTO toDTO() {

        return new AccountDTO().toBuilder().accountNo(this.accountNo).accountType(this.accountType).balance(this.balance)
                .currency(this.currency).isActive(this.isActive.equals('Y')).status(this.status).title(this.title)
                /*.customer(this.customer.toDTO())*/.build();
    }
}
