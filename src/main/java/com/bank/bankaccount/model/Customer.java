package com.bank.bankaccount.model;

import com.bank.bankaccount.dto.AccountDTO;
import com.bank.bankaccount.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Size(min = 1, max = 1)
    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "customer")
    private List<Account> accounts;

    @CreatedDate
    @Column(name = "created_on")
    private LocalDateTime creationDate;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_on")
    private LocalDateTime updatedDate;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    public CustomerDTO toDTO() {
        List<AccountDTO> accountDTOs = this.accounts.stream().map(Account::toDTO).collect(Collectors.toList());
        return new CustomerDTO().toBuilder().id(id).status(status).mobileNo(mobileNo)
                .gender(gender).firstName(firstName).lastName(lastName)
                .accounts(accountDTOs).build();
    }
}
