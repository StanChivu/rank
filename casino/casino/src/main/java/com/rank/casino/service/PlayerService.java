package com.rank.casino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rank.casino.repo.PlayerRepo;
import com.rank.casino.repo.TransactionRepo;
import com.rank.casino.model.Player;
import com.rank.casino.model.Transaction;

@Service
public class PlayerService {
    
    private final PlayerRepo playerRepo;
    private final TransactionRepo transRepo;

    @Autowired 
    public PlayerService(PlayerRepo playerRepo, TransactionRepo transRepo){
        this.playerRepo = playerRepo;
        this.transRepo = transRepo;

    }

    // Save new player
    public Player newPlayer(Player player){
        return playerRepo.save(player);
    }


    // Get all Players
    public List<Player> findAllPlayers(){
        return playerRepo.findAll();
    }
    
    public List<Player> findPlayerBalanceByPlayerId(int playerID) throws Exception{
        return playerRepo.findPlayerBalanceByPlayerID(playerID);       
    }

    // Update player
    public Player updatePlayerBalance(Player player) throws Exception {
        
        return playerRepo.save(player);

    }

    // save new Transaction
    public Transaction newTransaction(Transaction transaction){
        return transRepo.saveAndFlush(transaction);
    }

    // find player by username
    public Player findPlayerByUsername(String username) throws Exception{
        
        return playerRepo.findPlayerByUsername(username).orElseThrow(() -> new Exception("Player username not found"));
        
    }

    // get a list of player transaction by playerID
    public List<Transaction> findTransactionByPlayerId(int playerID) throws Exception{
        return transRepo.findTransactionByPlayerID(playerID);
    }

}
