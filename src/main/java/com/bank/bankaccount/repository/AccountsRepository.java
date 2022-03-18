package com.bank.bankaccount.repository;

import com.bank.bankaccount.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    /*@Override
    Optional<Account> findById(Long id);

    @Query("select a from Account a where a.account_no = :accountNo and a.isActive = 'Y'")
    Optional<Account> findByAccountNo(@Param("accountNo") String accountNo);

    @Query("select a from Accounts a where a.customer.id =:customerId and a.isActive ='Y'")
    List<Account> findByCustomerID(@Param("customerId") String customerId);

    @Query("select a from Accounts a where a.id=:id and a.accountType=:accountType and a.isActive ='Y'")
    Account getAccountByIdAndAccountType(@Param("id") String id, @Param("accountType") String accountType);*/
}
