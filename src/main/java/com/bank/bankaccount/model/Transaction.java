package com.bank.bankaccount.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

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

    @Column(name = "from_account_no")
    private String fromAccountNo;

    @Column(name = "to_account_no")
    private String toAccountNo;

    @Column(name = "txn_code")
    private String txnCode;

    @Column(name = "txn_type")
    private String txnType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "from_account_id")
    private String fromAccountId;

    @Column(name = "to_account_id")
    private String toAccountId;

    @Column(name = "from_customer_id")
    private String fromCustomerId;

    @Column(name = "to_customer_id")
    private String toCustomerId;

    @NotNull
    @Column(name = "currency")
    private String currency;

    @Column(name = "tax")
    private BigDecimal tax;

    @Column(name = "fee")
    private BigDecimal fee;

}
