package com.example.server.Results;

import com.example.server.Model.TicketToRideGame;

import java.util.List;

/**
 * Created by fryti on 1/29/2018.
 */

public class CreateGameResult extends Result {
    private TicketToRideGame game;

    public CreateGameResult(boolean isSuccess, String errorMessage, List<GenericCommand> clientCommands, String errorType, TicketToRideGame game){
        super(isSuccess, errorMessage, clientCommands, errorType, "CreateGameResult");
        this.game=game;

    }

    public TicketToRideGame getGame() {
        return game;
    }
}
