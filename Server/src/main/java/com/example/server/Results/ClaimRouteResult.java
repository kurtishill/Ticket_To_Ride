package com.example.server.Results;

import com.example.server.Model.TicketToRideGame;

import java.util.List;

/**
 * Created by fryti on 3/14/2018.
 */

public class ClaimRouteResult extends Result {
    private TicketToRideGame game;

    public ClaimRouteResult(boolean isSuccess, String errorMessage, List<ClientCommand> clientCommands,
                                          String errorType, TicketToRideGame game) {
        super(isSuccess, errorMessage, clientCommands, errorType, "SelectDestinationTicketsResult");
        this.game=game;
    }
    public TicketToRideGame getGame(){return game;}
}
