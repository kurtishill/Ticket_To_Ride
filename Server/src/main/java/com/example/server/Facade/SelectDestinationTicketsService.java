package com.example.server.Facade;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.GameHistory;
import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.SelectDestinationTicketsResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fryti on 2/25/2018.
 */

public class SelectDestinationTicketsService {
    public SelectDestinationTicketsResult select(String player, Integer gameId, ArrayList<DestinationCard> selectedRoutes, ArrayList<DestinationCard> discardedRoutes){
        //add selected cards to player object
        TicketToRideGame game = ModelRoot.instance().getAllGames().get(gameId);

        Player modelPlayer = new Player();
        //find the model's player object based on the given username String:player
        for(int i=0; i<game.getPlayers().size(); i++){
            if(game.getPlayers().get(i).getUsername().equals(player))
                modelPlayer=game.getPlayers().get(i);
        }


        //give the player the cards he selected
        for(int i=0; i<selectedRoutes.size(); i++)
            modelPlayer.addDestinationCard(selectedRoutes.get(i));
        //put the discarded ones at the bottom of the deck
        for(int i=0; i<discardedRoutes.size(); i++){
            game.getDeckDestinationCards().add(discardedRoutes.get(i));
        }

        if (modelPlayer.getState().equals("startup"))
            modelPlayer.setState("notStartup");
        else {
            GameHistory gameHistory = new GameHistory(modelPlayer.getUsername(), modelPlayer.getColor(),
                    "drew " + selectedRoutes.size() + " destination cards");
            game.addGameHistoryList(gameHistory);
            game.changeTurn();
        }
        return new SelectDestinationTicketsResult(true, null, null, null, game);
        //todo surround in try catch in case of bad result somehow?
    }

}
