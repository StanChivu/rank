package com.rank.casino.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction{
    
    public enum TransactionType{
        WAGER,
        WIN;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int transactionId;
    private float amount;
    private static TransactionType transactionType;
    private int playerID;

    public Transaction(){
        this.transactionId = 0;
        this.amount = 0.0f;
        Transaction.transactionType = null;
        this.playerID = 0;
    }

    public Transaction(int transactionId, float amount, TransactionType transactionType, int playerID) throws Exception{
        setTransactionId(transactionId);
        setAmount(amount);
        setTransactionType(transactionType);
        setPlayerID(playerID);
    }

    public Transaction(float amount, TransactionType transactionType, int playerID) throws Exception{
        setAmount(amount);
        setTransactionType(transactionType);
        setPlayerID(playerID);
    }
    
    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
    }

    public void setAmount(float amount){
        this.amount = amount;
    }
     

    public int getTransactionId() {
        return transactionId;
    }

    public float getAmount() {
        return amount;
    }

    public void setTransactionType( TransactionType transactionType) throws Exception {
            Transaction.transactionType = transactionType;
    }

    public TransactionType getTransactionType(){
        return transactionType;
    }
    
    public void setPlayerID(int playerID){
        this.playerID = playerID;
    }

    public int getPlayerID(){
        return playerID;
    }
    
}
