package com.example.server.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by tnels on 1/29/2018.
 */

public class TicketToRideGame {

    private List<Player> players;
    private String name;
    private int gameID;
    private int maxNumPlayers;
    private List<String> availableColors;

    public TicketToRideGame(){
        players = new ArrayList<>();
        name = null;
        gameID = 0;
        maxNumPlayers = 0;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
    }

    public TicketToRideGame(Player player) {
        players = new ArrayList<>();
        players.add(player);
        name = null;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
    }

    public TicketToRideGame(List<Player> players) {
        this.players = players;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
    }

    public TicketToRideGame(Player player,
                            String name,
                            int gameID,
                            int maxNumPlayers) {
        players = new ArrayList<>();
        players.add(player);
        this.name = name;
        this.gameID = gameID;
        this.maxNumPlayers = maxNumPlayers;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
    }

    public List<Player> getPlayers() {
        return players;
    }




    public void addPlayer(Player player)
    {
        if(players.size() < 5)
            players.add(player);
    }

    public String getName() {
        return name;
    }

    public int getGameID() {
        return gameID;
    }

    public int getMaxNumPlayers() {
        return maxNumPlayers;
    }

    public List<String> getAvailableColors(){return availableColors;}

    public void setName(String name) {
        this.name = name;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setMaxNumPlayers(int maxNumPlayers) {
        this.maxNumPlayers = maxNumPlayers;
    }

    public void setAvailableColors(List<String> availableColors){ this.availableColors=availableColors;}
}
