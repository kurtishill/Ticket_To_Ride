package com.example.server.Results;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.TicketToRideGame;

import java.util.List;

/**
 * Created by fryti on 2/25/2018.
 */

public class DrawDestinationTicketsResult extends Result {
    private List<DestinationCard> destinationCards;

    public DrawDestinationTicketsResult(boolean isSuccess, String errorMessage, List<ClientCommand> clientCommands,
                                        String errorType, List<DestinationCard> cards) {
        super(isSuccess, errorMessage, clientCommands, errorType, "DrawDestinationTicketsResult");
        destinationCards = cards;
    }
    public List<DestinationCard> getDestinationCards(){return destinationCards;}
}
