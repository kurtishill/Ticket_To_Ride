package com.example.server.Model;

import java.util.Map;

/**
 * Created by tnels on 1/29/2018.
 */

public class ModelRoot {

    private ModelRoot _ModelRoot_Instance;
    private Map<Integer, TicketToRideGame> allGames;
    private Map<String, Player> allPlayers;
    private String mAuthToken;

    private ModelRoot() {}

    public ModelRoot instance() {
        if(_ModelRoot_Instance == null)
            return new ModelRoot();
        return _ModelRoot_Instance;
    }

    private void addGame(int gameID, TicketToRideGame game) {
        allGames.put(gameID, game);
    }

    private void allPlayer(String username, Player player) {
        allPlayers.put(username, player);
    }

    private Map<Integer, TicketToRideGame> getAllGames() {
        return allGames;
    }

    private Map<String, Player> getAllPlayers() {
        return allPlayers;
    }

    private void setAllGames(Map<Integer, TicketToRideGame> allGames) {
        this.allGames = allGames;
    }

    private void setAllPlayers(Map<String, Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

    private String getmAuthToken() {
        return mAuthToken;
    }

    private void setmAuthToken(String mAuthToken) {
        this.mAuthToken = mAuthToken;
    }
}
