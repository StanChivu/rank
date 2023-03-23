package com.rank.casino.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rank.casino.model.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
    
    @Query(value="SELECT * FROM Transaction WHERE playerID LIKE :playerId order by transaction_ID desc LIMIT 10", nativeQuery = true)
    List<Transaction> findTransactionByPlayerID( int playerId);

}
