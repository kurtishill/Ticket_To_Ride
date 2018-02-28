package com.example.server.Model;

import java.util.UUID;

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

    private List<TrainCard> trainCards;

    private List<DestinationCard> destinationCards;

    private List<Route> claimedRoutes;

    public Player() {
        this.username = null;
        this.password = null;
        numPoints = 0;
        numTrainCars = 45;
        color = null;
        id = UUID.randomUUID().toString();
        trainCards = new ArrayList<>();
        destinationCards = new ArrayList<>();
        claimedRoutes = new ArrayList<>();
    }

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
        color = null;
        id = UUID.randomUUID().toString();
        trainCards = new ArrayList<>();
        destinationCards = new ArrayList<>();
        claimedRoutes = new ArrayList<>();
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

    public void setId() {
        id = UUID.randomUUID().toString();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void addTrainCard(TrainCard card) {
        trainCards.add(card);
    }

    public void removeTrainCard(TrainCard card) {
        for(int i = 0; i < trainCards.size(); i++) {
            if(trainCards.at(i).getColor().equals(card.getColor()))
            {
                trainCards.remove(i);
                break;
            }
        }
    }

    public void addDestinationCard(DestinationCard card) {
        destinationCards.add(card);
    }

    public void removeDestinationCard(DestinationCard card) {
        for(int i = 0; i < destinationCards.size(); i++) {
            if(destinationCards.at(i).equals(card))
            {
                destinationCards.remove(i);
                break;
            }
        }
    }

    public List<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public void addRoute(Route route) {
        claimedRoutes.add(route);
    }
}
