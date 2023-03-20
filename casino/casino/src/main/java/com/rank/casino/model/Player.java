package com.rank.casino.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Player{
    
    @Id
    @Column(unique = true,nullable = false, updatable = false)    
    private int playerID;
    private String username;
    private String firstName;
    private String lastName;
    private float balance;


    // Default and populated Constructor

    //Initialization class - Default contructor
    public Player(){
        playerID = 0;
        username = "null";
        firstName = "null";
        lastName = "null";
        balance = 0.0f;

    }

    public Player(int playerID,String username, String firstName, String lastName, float balance) throws Exception{
        setPlayerID(playerID);
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setBalance(balance);
    }

    public Player(int playerID, float balance){
        setPlayerID(playerID);
        setBalance(balance);
    }

    // Set Methods

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    // validate username (+3 characters)
    public void setUsername(String username) throws Exception {
        
        if( username.length() > 3 )
        {
            this.username = username;
        }else{
            throw new Exception("Username must contain atleast 3 words");
        }
        
    }


    // validate First name (+3 characters)
    public void setFirstName(String firstName) throws Exception{
        
        if( firstName.length() > 3 )
        {
            this.firstName = firstName;
        }else{
             throw new Exception("First name must contain atleast 3 words");
        }
        
    }

    // validate Last name (+3 characters)
    public void setLastName(String lastName) throws Exception{
        
        if( lastName.length() > 3 )
        {
            this.lastName = lastName;
        }else{
             throw new Exception("Last name must contain atleast 3 words");
        }
        
    }

    // validate balance
    public void setBalance(float balance){
        this.balance = balance;
    }

    // get methods

    public int getPlayerID(){
        return playerID;
    }

    public String getUsername(){
        return username;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public float getBalance(){
        return balance;
    }
    
    @Override
    public String toString(){
        return "Player(" +
                "playerID = " + playerID +
                ", Userame ='" + username +'\''+
                ", First Name ='" + firstName +'\''+  
                ", Last Name = '" + lastName +'\''+
                ", Balance = " + balance + ")";
    }    


}
