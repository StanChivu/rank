package com.rank.casino.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rank.casino.model.Player;

public interface PlayerRepo extends JpaRepository<Player, Integer> {

    public static String GET_PLAYER_BAL = "SELECT p FROM player p WHERE p.playerID = :playerID";
    // ResponseEntity<Player>
    // ResponseEntity<Player> findPlayerById(int playerId);
    
    // Optional<Player> getPlayerBalance( int playerId );
 
    //@Query(value="SELECT playerid, balance FROM player where playerID=:playerId", nativeQuery = true)
    //@Query(value = GET_PLAYER_BAL, nativeQuery = true)
    List<Player> findPlayerBalanceByPlayerID(int playerID);

    
}  