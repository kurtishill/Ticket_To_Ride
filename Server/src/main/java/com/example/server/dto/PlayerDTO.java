package com.example.server.dto;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.Route;
import com.example.server.Model.TrainCard;

import java.util.List;

/**
 * Created by kurtishill on 4/9/18.
 */

public class PlayerDTO {

    private String id;

    private String username;

    private String password;

    private int numPoints;

    private int numTrainCars;

    private String color;

    private List<TrainCard> trainCards;

    private List<DestinationCard> destinationCards;

    private List<Route> claimedRoutes;

    private String state;

    private int longestPathLength;

    private int hasLongestPath;

    public PlayerDTO() {}

    public PlayerDTO(String id, String username, String password, int numPoints, int numTrainCars, String color,
                     List<TrainCard> trainCards, List<DestinationCard> destinationCards, List<Route> claimedRoutes,
                     String state, int longestPathLength, int hasLongestPath) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.numPoints = numPoints;
        this.numTrainCars = numTrainCars;
        this.color = color;
        this.trainCards = trainCards;
        this.destinationCards = destinationCards;
        this.claimedRoutes = claimedRoutes;
        this.state = state;
        this.longestPathLength = longestPathLength;
        this.hasLongestPath = hasLongestPath;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public int getNumTrainCars() {
        return numTrainCars;
    }

    public String getColor() {
        return color;
    }

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }

    public List<DestinationCard> getDestinationCards() {
        return destinationCards;
    }

    public List<Route> getClaimedRoutes() {
        return claimedRoutes;
    }

    public String getState() {
        return state;
    }

    public int getLongestPathLength() {
        return longestPathLength;
    }

    public int getHasLongestPath() {
        return hasLongestPath;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public void setNumTrainCars(int numTrainCars) {
        this.numTrainCars = numTrainCars;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTrainCards(List<TrainCard> trainCards) {
        this.trainCards = trainCards;
    }

    public void setDestinationCards(List<DestinationCard> destinationCards) {
        this.destinationCards = destinationCards;
    }

    public void setClaimedRoutes(List<Route> claimedRoutes) {
        this.claimedRoutes = claimedRoutes;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLongestPathLength(int longestPathLength) {
        this.longestPathLength = longestPathLength;
    }

    public void setHasLongestPath(int hasLongestPath) {
        this.hasLongestPath = hasLongestPath;
    }
}
