package com.example.server.Model;

/**
 * Created by tnels on 1/29/2018.
 */

public class Player {

    private String username;

    private String password;

    private int numPoints;

    private int numTrainCars;

    private String color;

    private String id;

    public Player() {
        this.username = null;
        this.password = null;
        numPoints = 0;
        numTrainCars = 45;
        color = null;
        id = null;
    }

    /**
     * Constructor for player class. Requires username and password to login to game.
     * @param username A player's username to login.
     * @param password A player's password to login.
 origin/client_views_presenters_guifacade_clientmode
     * Points start and 0, and number of train cars starts at 45.3
     */
    public Player(String username, String password)
    {
        this.username = username;
        this.password = password;
        numPoints = 0;
        numTrainCars = 45;
        color = null;
        id = null;
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

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
