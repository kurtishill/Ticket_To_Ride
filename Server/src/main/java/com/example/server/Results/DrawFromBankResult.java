package com.example.server.Results;

import com.example.server.Model.TicketToRideGame;
import com.example.server.Model.TrainCard;

import java.util.List;

/**
 * Created by kurtis on 3/2/18.
 */

public class DrawFromBankResult extends Result {
    private TicketToRideGame game;

    public DrawFromBankResult(boolean isSuccess, String errorMessage, List<ClientCommand> clientCommands, String errorType,
                              TicketToRideGame game) {
        super(isSuccess, errorMessage, clientCommands, errorType, "DrawFromBankResult");
        this.game = game;
    }

    public TicketToRideGame getGame() {
        return game;
    }
}
