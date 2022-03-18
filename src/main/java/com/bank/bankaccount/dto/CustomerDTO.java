package com.bank.bankaccount.dto;

import com.bank.bankaccount.model.Customer;
import lombok.*;


@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long id;
    private String status;
    private String mobileNo;
    private String gender;
    private String firstName;
    private String lastName;

    public Customer toEntity() {
        return new Customer().toBuilder().id(id).status(status).mobileNo(mobileNo)
                .gender(gender).firstName(firstName).lastName(lastName).build();
    }
}
