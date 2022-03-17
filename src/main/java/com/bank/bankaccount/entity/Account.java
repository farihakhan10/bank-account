package com.bank.bankaccount.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
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

    @Column(name = "iban")
    private String iBan;

    @Column(name="account_no")
    private String accountNo;

    @NotNull
    @Size(max = 20)
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @ManyToOne(optional = false)
    @NotNull
    private Customer customer;

    @NotNull
    @Column(name="currency")
    private String currency;

    @NotNull
    @Column(name="balance")
    private BigDecimal balance;

}
