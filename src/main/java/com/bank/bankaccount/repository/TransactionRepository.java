package com.bank.bankaccount.repository;

import com.bank.bankaccount.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Override
    Optional<Transaction> findById(Long aLong);
}