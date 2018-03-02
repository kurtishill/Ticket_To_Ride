package com.example.server.Facade;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.DrawDestinationTicketsResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fryti on 2/25/2018.
 */

public class DrawDestinationTicketsService {

    public DrawDestinationTicketsResult draw(String playerName, int gameId){
        TicketToRideGame game = ModelRoot.instance().getAllGames().get(gameId);
        Player player;
        for(int i=0; i<ModelRoot.instance().getAllGames().get(gameId).getPlayers().size(); i++){
            if(ModelRoot.instance().getAllGames().get(gameId).getPlayers().get(i).getUsername().equals(playerName))
                player = ModelRoot.instance().getAllGames().get(gameId).getPlayers().get(i);
        }
        List<DestinationCard> drawnCards = new ArrayList<>();
        for(int i=0; i<3; i++) {
            if(game.getDeckDestinationCards().size()!=0)
                drawnCards.add(game.getDeckDestinationCards().remove(0));
        }

        if(drawnCards.size()>0) { //ALL clients need to update the destination ticket deck, but only client with player receive the drawn cards
            return new DrawDestinationTicketsResult(true, null, null, null, drawnCards);
        }
        else{
            return new DrawDestinationTicketsResult(false, "Destination ticket deck is empty!", null, "EmptyDeck", drawnCards);
        }

    }
}
