package com.example.server.Facade;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.SelectDestinationTicketsResult;

import java.util.List;

/**
 * Created by fryti on 2/25/2018.
 */

public class SelectDestinationTicketsService {
    public SelectDestinationTicketsResult select(Player player, int gameId, List<DestinationCard> selectedRoutes, List<DestinationCard> discardedRoutes){
        //add selected cards to player object
        TicketToRideGame game = ModelRoot.instance().getAllGames().get(gameId);
        int playerInt = game.getPlayers().indexOf(player);
        Player modelPlayer = game.getPlayers().get(playerInt);
        //give the player the cards he selected
        for(int i=0; i<selectedRoutes.size(); i++)
            modelPlayer.addDestinationCard(selectedRoutes.get(i));
        //put the discarded ones at the bottom of the deck
        for(int i=0; i<discardedRoutes.size(); i++){
            game.getDeckDestinationCards().add(discardedRoutes.get(i));
        }
        return new SelectDestinationTicketsResult(true, null, null, null, selectedRoutes, discardedRoutes);
        //todo surround in try catch in case of bad result somehow?
    }

}
