package com.bank.bankaccount.model;

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
public class Transaction extends AbstractAuditing implements Serializable {

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
    private Account fromAccount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Account toAccount;
}
