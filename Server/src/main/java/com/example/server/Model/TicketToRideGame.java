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
    private List<TrainCard> deckTrainCards;
    private List<DestinationCard> deckDestinationCards;
    private List<TrainCard> faceUpCards;
    private List<Route> availableRoutes;

    public TicketToRideGame(){
        players = new ArrayList<>();
        name = null;
        gameID = 0;
        maxNumPlayers = 0;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
        chat = new ArrayList<>();
        deckTrainCards = new ArrayList<>();
        deckDestinationCards = new ArrayList<>();
        faceUpCards = new ArrayList<>();
        availableRoutes = new ArrayList<>();
    }

    public TicketToRideGame(Player player) {
        players = new ArrayList<>();
        players.add(player);
        name = null;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
        chat = new ArrayList<>();
        deckTrainCards = new ArrayList<>();
        deckDestinationCards = new ArrayList<>();
        faceUpCards = new ArrayList<>();
        availableRoutes = new ArrayList<>();
    }

    public TicketToRideGame(List<Player> players) {
        this.players = players;
        availableColors = new ArrayList<>();
        availableColors.addAll(Arrays.asList("red", "blue", "yellow", "green", "black"));
        chat = new ArrayList<>();
        deckTrainCards = new ArrayList<>();
        deckDestinationCards = new ArrayList<>();
        faceUpCards = new ArrayList<>();
        availableRoutes = new ArrayList<>();
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
        deckTrainCards = new ArrayList<>();
        deckDestinationCards = new ArrayList<>();
        faceUpCards = new ArrayList<>();
        availableRoutes = new ArrayList<>();
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

    public List<TrainCard> getDeckTrainCards() {
        return deckTrainCards;
    }

    public void addTrainCard(TrainCard card) {
        deckTrainCards.add(card);
    }

    public void removeTrainCard(TrainCard card) {
        for(int i = 0; i < deckTrainCards.size(); i++) {
            if(deckTrainCards.get(i).equals(card))
            {
                deckTrainCards.remove(i);
                break;
            }
        }
    }

    public List<DestinationCard> getDeckDestinationCards() {
        return deckDestinationCards;
    }

    public void addDestinationCard(DestinationCard card) {
        deckDestinationCards.add(card);
    }

    public void removeDestinationCard(DestinationCard card)
    {
        for(int i = 0; i < deckDestinationCards.size(); i++)
        {
            if(deckDestinationCards.get(i).equals(card))
            {
                deckDestinationCards.remove(i);
                break;
            }
        }
    }

    public List<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public void addFaceUpCard()
    {
        if(faceUpCards.size() < 5)
        {
            faceUpCards.add(deckTrainCards.get(0));
            deckTrainCards.remove(0);
        }
    }

    public removeFaceUpCard(TrainCard card)
    {
        for(int i = 0; i < faceUpCards.size(); i++)
        {
            if(faceUpCards.get(i).equals(card))
            {
                faceUpCards.remove(i);
                break;
            }
        }
    }

    public List<Route> getAvailableRoutes() {
        return availableRoutes;
    }

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
