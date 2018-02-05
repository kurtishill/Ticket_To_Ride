package com.example.server.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tnels on 1/29/2018.
 */

public class ModelRoot {

    private static ModelRoot _ModelRoot_Instance;
    private Map<Integer, TicketToRideGame> allGames;
    private List<TicketToRideGame> listGames;
    private Map<String, Player> allPlayers;

    private ModelRoot() {
        allGames = new HashMap<>();
        listGames = new ArrayList<>();
        allPlayers = new HashMap<>();
    }

    public TicketToRideGame GameExists(int gameId){
        if(allGames.containsKey(gameId)){
            return allGames.get(gameId);
        }
        else {
            return null;
        }
    }

    public static ModelRoot instance() {
        if(_ModelRoot_Instance == null)
            _ModelRoot_Instance = new ModelRoot();
        return _ModelRoot_Instance;
    }

    public void addGame(int gameID, TicketToRideGame game) {
        allGames.put(gameID, game);
        listGames.add(game);
    }

    public void allPlayer(String username, Player player) {
        allPlayers.put(username, player);
    }

    public Player UserExists(String username){
        if(allPlayers.containsKey(username)){
            return allPlayers.get(username);
        }
        else{
            return null;
        }
    }

    private Map<Integer, TicketToRideGame> getAllGames() {
        return allGames;
    }

    private Map<String, Player> getAllPlayers() {
        return allPlayers;
    }

    public List<TicketToRideGame> getListGames() {
        return listGames;
    }

    private void setAllGames(Map<Integer, TicketToRideGame> allGames) {
        this.allGames = allGames;
    }

    private void setAllPlayers(Map<String, Player> allPlayers) {
        this.allPlayers = allPlayers;
    }
}
