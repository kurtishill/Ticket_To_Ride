package com.example.server.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tnels on 1/29/2018.
 */

public class TicketToRideGame {

    private List<Player> players;

    private int gameID;

    public TicketToRideGame(Player player)
    {
        players = new ArrayList<>();
        players.add(player);

    }


    public TicketToRideGame(List<Player> players)
    {
        this.players = players;
    }

    public void addPlayer(Player player)
    {
        if(players.size() < 5)
            players.add(player);
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
