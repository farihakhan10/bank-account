package com.bank.bankaccount.repository;

import com.bank.bankaccount.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    @Query("select a from Account a where a.accountNo = :accountNo and a.isActive = 'Y'")
    Optional<Account> findByAccountNo(@Param("accountNo") Long accountNo);

    @Query("select a from Account a where a.id = :accountId and a.isActive = 'Y'")
    Optional<Account> findByAccountId(@Param("accountId") Long accountId);

    @Query("select a from Account a where a.customer.id =:customerId and a.isActive ='Y'")
    Optional<List<Account>> findByCustomerID(@Param("customerId") Long customerId);

    @Query("select a from Account a where a.id=:id and a.accountType=:accountType and a.isActive ='Y'")
    Optional<List<Account>> getAccountByIdAndAccountType(@Param("id") Long id, @Param("accountType") String accountType);

    @Query("select a.balance from Account a where a.accountNo = :accountNo and a.isActive = 'Y'")
    Double getAccountBalance(@Param("accountNo") Long accountNo);

    @Query("select a.accountNo from Account a where a.id = :id and a.isActive = 'Y'")
    Long getAccountNoById(@Param("id") Long id);
}
