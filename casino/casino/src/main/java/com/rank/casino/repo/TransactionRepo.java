package com.rank.casino.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rank.casino.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
    
}
