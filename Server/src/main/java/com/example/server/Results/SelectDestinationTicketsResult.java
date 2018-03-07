package com.example.server.Results;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.TicketToRideGame;

import java.util.List;

import sun.security.krb5.internal.crypto.Des;

/**
 * Created by fryti on 2/26/2018.
 */

public class SelectDestinationTicketsResult extends Result {
   private TicketToRideGame game;

    public SelectDestinationTicketsResult(boolean isSuccess, String errorMessage, List<ClientCommand> clientCommands,
                                        String errorType, TicketToRideGame game) {
        super(isSuccess, errorMessage, clientCommands, errorType, "SelectDestinationTicketsResult");
        this.game=game;
    }
    public TicketToRideGame getGame(){return game;}
}
