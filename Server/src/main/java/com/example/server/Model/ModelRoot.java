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

    public void allPlayer(String authToken, Player player) {
        allPlayers.put(authToken, player);
    }

    public Player UserExists(String authToken){
        if(allPlayers.containsKey(authToken)){
            return allPlayers.get(authToken);
        }
        else{
            return null;
        }
    }

    public boolean UserNameExists(String username) {
        boolean check = false;
        for (Map.Entry<String, Player> entry : allPlayers.entrySet()) {
            if (entry.getValue().getUsername().equals(username))
                check = true;
        }
        return check;
    }

    public Player getPlayerByUserName(String username) {
        for (Map.Entry<String, Player> entry : allPlayers.entrySet()) {
            if (entry.getValue().getUsername().equals(username))
                return entry.getValue();
        }
        return null;
    }

    public Map<Integer, TicketToRideGame> getAllGames() {
        return allGames;
    }

    public Map<String, Player> getAllPlayers() {
        return allPlayers;
    }

    public List<TicketToRideGame> getListGames() {
        return listGames;
    }

    public void setAllGames(Map<Integer, TicketToRideGame> allGames) {
        this.allGames = allGames;
    }

    public void setAllPlayers(Map<String, Player> allPlayers) {
        this.allPlayers = allPlayers;
    }

}
