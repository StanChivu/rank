package com.rank.casino.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.grammars.hql.HqlParser.IsEmptyPredicateContext;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rank.casino.model.Player;
import com.rank.casino.model.Transaction;
import com.rank.casino.model.Transaction.TransactionType;
import com.rank.casino.service.PlayerService;

@RestController
@RequestMapping("/casino")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    // insert a player in the database
    @PostMapping("/player/savePlayer")
    public String savePlayer(@RequestBody Player player){
        try {
            playerService.newPlayer(player);
            return "Player: " + player.getFirstName() +" " + player.getLastName() + " saved successfullly";
    
        } catch (Exception e) {
            return "Error occured while saving: " + e.getMessage();
        }
    }

        // Get All Players
        @GetMapping("/player/getAllPlayers")
        public List<Player> getAll(){
            return playerService.findAllPlayers();
        }


        // Get the player balance
        @GetMapping("player/{playerID}/balance")
        public ResponseEntity<List<Player>> findPlayerBalanceByPlayerId(@PathVariable("playerID") int playerID) throws Exception{
            System.out.println("Player ID: " + playerID);

            try {
                List<Player> playerBalance = playerService.findPlayerBalanceByPlayerId(playerID);
                
                if( playerBalance.isEmpty() ){
                    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
                }else{
                    return new ResponseEntity<>(playerBalance,HttpStatus.OK);
                }

            } catch (Exception e) {
                
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
                //return "returnBalance";
            }
            
        }

        // Update player balance
        @PutMapping("/player/{playerid}/balance/update")
        public ResponseEntity<List<String>> updatePlayerBalance(@PathVariable("playerid") int playerID, @RequestBody Transaction transaction ) throws Exception{
            
            
           try {
                float currentAmount = 0.0f;
                float amount = transaction.getAmount();
                 
               
                List<String> playerTransaction = new ArrayList<>();

                TransactionType transactionType = transaction.getTransactionType();
                
                System.out.println("Amount: " + amount + " Transaction type: " + transactionType);
                transaction.setPlayerID(playerID);
                Transaction lastTransaction = new Transaction();
                System.out.println("PlayerID: " + transaction.getPlayerID() );

                // if the amount is negative (less than 0), show as HTTP Bad req
                if( amount < 0 ){
                    System.out.println("Amount is negative");
                    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
                    //throw new Exception("Amount must not be negative");
                }

                List<Player> player = playerService.findPlayerBalanceByPlayerId(playerID);
                
                if( player.isEmpty() ){
                    return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
                }else{
                    for( int i = 0; i < player.size(); i++ ){
                        currentAmount = player.get(i).getBalance();
                    }
                }

                // if transaction is WAGER and the amount is greater than the player balance, return a teapot
                if( transactionType == TransactionType.WAGER & amount > currentAmount )
                {
                    return new ResponseEntity<>(null,HttpStatus.I_AM_A_TEAPOT );
                }
                else if( transactionType == TransactionType.WAGER & amount <= currentAmount )
                {
                    // update the player balance.
                    player.get(0).setBalance( currentAmount - amount);
                    float f = currentAmount - amount;
                    System.out.println("New player balance should be: " + f);

                    // update player balance
                    playerService.updatePlayerBalance(player.get(0));
                    
                    // save new transaction
                    lastTransaction = playerService.newTransaction(transaction);
                    //List<String> playerTransaction = new ArrayList<>();

                    playerTransaction.add("transactionId: " + lastTransaction.getTransactionId() );
                    playerTransaction.add("amount: " + player.get(0).getBalance() );

                    System.out.print("Last transaction ID: " + lastTransaction.getTransactionId() );
                    
                    // save transaction list
                    //List<Transaction> transactionStatus = new List<Transaction>();

                }

                if( transactionType == TransactionType.WIN ){
                    player.get(0).setBalance( currentAmount + amount);
                    
                    playerService.updatePlayerBalance(player.get(0));

                    // save new transaction
                    lastTransaction = playerService.newTransaction(transaction);
                    

                    playerTransaction.add("transactionId: " + lastTransaction.getTransactionId() );
                    playerTransaction.add("amount: " + player.get(0).getBalance() );

                    System.out.print("Last transaction ID: " + lastTransaction.getTransactionId() );

                    // save transaction list
                    //List<Transaction,Player> transactionStatus = new List<>(lastTransaction, player);
                    //return new ResponseEntity<>( lastTransaction , HttpStatus.OK);
                    //return new ResponseEntity<>( transactionStatus , HttpStatus.OK);

                }

                return new ResponseEntity<>( playerTransaction , HttpStatus.OK);
                
            } catch (Exception e) {
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
            

        }

 /**  
        // Get player
        @GetMapping("/player/{playerId}")
        public ResponseEntity<Player> getPlayerById(@PathVariable("playerId") int playerId ){
            return playerService.findPlayerById(playerId);
        }

/**
        
        // Get the player balance
        @GetMapping("player/{playerID}/balance")
        public Player getPlayerBalanceById(int playerId){
            
        }
    
        // Update the player balance
        @PostMapping("/player/{playerid}/balance/update")
        public Player updateBalance(@RequestBody Player player, @RequestBody Transaction transaction){
            
        }

    */

}
