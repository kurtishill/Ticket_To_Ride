package com.example.server.Results;

import com.example.server.Model.DestinationCard;

import java.util.List;

import sun.security.krb5.internal.crypto.Des;

/**
 * Created by fryti on 2/26/2018.
 */

public class SelectDestinationTicketsResult extends Result {
    private List<DestinationCard> selectedDestinationCards;
    private List<DestinationCard> discardedDestinationCards;

    public SelectDestinationTicketsResult(boolean isSuccess, String errorMessage, List<ClientCommand> clientCommands,
                                        String errorType, List<DestinationCard> selectedCards, List<DestinationCard> discardedCards) {
        super(isSuccess, errorMessage, clientCommands, errorType, "SelectDestinationTicketsResult");
        selectedDestinationCards = selectedCards;
        discardedDestinationCards = discardedCards;
    }
    public List<DestinationCard> getSelectedDestinationCards(){return selectedDestinationCards;}
    public List<DestinationCard> getDiscardedDestinationCards(){return discardedDestinationCards;}
}
