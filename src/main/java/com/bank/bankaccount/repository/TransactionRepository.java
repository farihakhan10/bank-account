package com.bank.bankaccount.repository;

import com.bank.bankaccount.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM TRANSACTIONS where (from_account_id = :accountID or to_account_id = :accountID) and status = 'COMPLETED'", nativeQuery = true)
    List<Transaction> getAccountTransactions(@Param("accountID") Long accountID);

}