package com.example.server.Model;

/**
 * Created by tnels on 1/29/2018.
 */

public class Player {

    private String username;

    private String password;

    private int numPoints;

    private int numTrainCars;


    /**
     * Constructor for player class. Requires username and password to login to game.
     * @param username A player's username to login.
     * @param password A player's password to login.
     * Points start and 0, and number of train cars starts at 45.3
     */
    public Player(String username, String password)
    {
        this.username = username;
        this.password = password;
        numPoints = 0;
        numTrainCars = 45;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public int getNumTrainCars() {
        return numTrainCars;
    }

    public void setNumTrainCars(int numTrainCars) {
        this.numTrainCars = numTrainCars;
    }


}
