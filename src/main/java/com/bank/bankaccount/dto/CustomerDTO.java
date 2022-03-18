package com.bank.bankaccount.dto;

import com.bank.bankaccount.model.Customer;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private String status;
    private String email;
    private String mobileNo;
    private String gender;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public Customer toEntity() {
        return new Customer().toBuilder().id(id).status(status).email(email).mobileNo(mobileNo)
                .gender(gender).firstName(firstName).lastName(lastName).dob(dob).build();
    }
}
