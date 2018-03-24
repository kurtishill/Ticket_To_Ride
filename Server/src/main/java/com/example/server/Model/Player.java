package com.example.server.Model;

import java.util.ArrayList;
import java.util.List;
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

    private String state;

    private int longestPathLength;

    private int hasLongestPath = 0;

    public void setLongestPathLength(int path){
        longestPathLength = path;
    }

    public int getLongestPathLength(){
        return longestPathLength;
    }

    public int getHasLongestPath(){
        return hasLongestPath;
    }
    public void setHasLongestPath(int path){
        hasLongestPath = path;
    }

    public Player() {
        this.username = null;
        this.password = null;
        numPoints = 0;
        numTrainCars = 10;
        color = null;
        id = UUID.randomUUID().toString();
        trainCards = new ArrayList<>();
        destinationCards = new ArrayList<>();
        claimedRoutes = new ArrayList<>();
        state = "startup";
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
        numTrainCars = 3;
        color = null;
        id = UUID.randomUUID().toString();
        trainCards = new ArrayList<>();
        destinationCards = new ArrayList<>();
        claimedRoutes = new ArrayList<>();
        state = "startup";
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

    public void subtractTrains(int num) {
        numTrainCars -= num;
    }

    public void removeTrainCard(TrainCard card) {
        for(int i = 0; i < trainCards.size(); i++) {
            if(trainCards.get(i).getColor().equals(card.getColor()))
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
            if(destinationCards.get(i).equals(card))
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
        numPoints+=route.getPointValue();
    }
    //after a player finishes a game, this will reset points, trains, etc, to the appropriate values
    public void resetPlayer(){
        this.setNumTrainCars(45);
        this.setNumPoints(0);
        this.setState("startup");
        this.getClaimedRoutes().clear();
        this.getDestinationCards().clear();
        this.getTrainCards().clear();

    }

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }

    public List<DestinationCard> getDestinationCards() {
        return destinationCards;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
