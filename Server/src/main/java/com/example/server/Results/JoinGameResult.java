package com.example.server.Results;

import com.example.server.Model.TicketToRideGame;

import java.util.List;

/**
 * Created by fryti on 1/29/2018.
 */

public class JoinGameResult extends Result {
    private TicketToRideGame game;

    public JoinGameResult(boolean isSuccess, String errorMessage, List<ClientCommand> clientCommands, String errorType, TicketToRideGame game) {
        super(isSuccess, errorMessage, clientCommands, errorType, "JoinGameResult");
        this.game=game;
    }

    public TicketToRideGame getGame() {
        return game;
    }
}
