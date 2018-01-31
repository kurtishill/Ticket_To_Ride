package com.example.server.Results;

import com.example.server.Model.TicketToRideGame;

import java.util.List;

/**
 * Created by fryti on 1/29/2018.
 */

public class GetGameListResult extends Result {
    private List<TicketToRideGame> games;

    public GetGameListResult(boolean isSuccess, String errorMessage, List<GenericCommand> clientCommands, String errorType, List<TicketToRideGame> games) {
        super(isSuccess, errorMessage, clientCommands, errorType);
        this.games=games;
    }

    public List<TicketToRideGame> getGames(){
        return games;
    }
}