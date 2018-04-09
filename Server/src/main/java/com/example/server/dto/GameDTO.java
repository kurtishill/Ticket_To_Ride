package com.example.server.dto;

import com.example.server.Model.TicketToRideGame;

/**
 * Created by kurtishill on 4/9/18.
 */

public class GameDTO {

    private int id;

    private TicketToRideGame game;

    public GameDTO() {}

    public GameDTO(int id, TicketToRideGame game) {
        this.id = id;
        this.game = game;
    }

    public int getId() {
        return id;
    }

    public TicketToRideGame getGame() {
        return game;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGame(TicketToRideGame game) {
        this.game = game;
    }
}
