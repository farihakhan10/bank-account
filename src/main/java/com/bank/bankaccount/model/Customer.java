package com.bank.bankaccount.model;

import com.bank.bankaccount.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "customers")
public class Customer extends AbstractAuditing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Size(max = 80)
    @Column(name = "email", length = 80)
    private String email;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Size(min = 1, max = 1)
    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private LocalDate dob;

    public CustomerDTO toDTO() {
        return new CustomerDTO().toBuilder().id(id).status(status).email(email).mobileNo(mobileNo)
                .gender(gender).firstName(firstName).lastName(lastName).dob(dob).build();
    }
}