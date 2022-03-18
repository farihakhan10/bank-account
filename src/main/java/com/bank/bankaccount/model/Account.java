package com.bank.bankaccount.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "accounts")
public class Account extends AbstractAuditing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_type", nullable = false)
    private String accountType;

    @Column(name = "is_active")
    private Character isActive;

    @Column(name = "title")
    private String title;

    @Column(name = "iban")
    private String iBan;

    @Column(name="account_no")
    private String accountNo;

    @NotNull
    @Size(max = 20)
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull
    private Customer customer;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    @NotNull
    @Column(name="currency")
    private String currency;

    @NotNull
    @Column(name="balance")
    private BigDecimal balance;

}
