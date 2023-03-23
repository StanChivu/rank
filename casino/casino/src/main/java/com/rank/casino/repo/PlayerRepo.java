package com.rank.casino.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rank.casino.model.Player;

public interface PlayerRepo extends JpaRepository<Player, Integer> {

    @Query(value="SELECT * FROM player WHERE username LIKE :username", nativeQuery = true)
    Optional<Player> findPlayerByUsername( String username );
 
    List<Player> findPlayerBalanceByPlayerID(int playerID);

    
}  