package com.example.server.Model;

import com.example.server.ChatMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;

/**
 * Created by tnels on 1/29/2018.
 */

public class TicketToRideGame {

    private List<Player> players;
    private String name;
    private int gameID;
    private int maxNumPlayers;
    private List<String> availableColors;
    private List<ChatMessage> chat;

    public TicketToRideGame(){
        players = new ArrayList<>();
        name = null;
        gameID = 0;
        maxNumPlayers = 0;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
        chat = new ArrayList<>();
    }

    public TicketToRideGame(Player player) {
        players = new ArrayList<>();
        players.add(player);
        name = null;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
        chat = new ArrayList<>();
    }

    public TicketToRideGame(List<Player> players) {
        this.players = players;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
        chat = new ArrayList<>();
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
        chat = new ArrayList<>();
    }

    public List<Player> getPlayers() {
        return players;
    }


    public List<ChatMessage> getChat() {
        return chat;
    }

    public void setChat(ChatMessage message) {
        this.chat.add(message);
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null)
            return false;
        if (o.getClass() != this.getClass())
            return false;

        TicketToRideGame other = (TicketToRideGame) o;

        return other.getGameID() == this.getGameID();

    }

    @Override
    public int hashCode() {
        return 31 * gameID + maxNumPlayers + name.hashCode();
    }
}
