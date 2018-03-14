package com.example.server.Facade;

import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.Route;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Model.TrainCard;
import com.example.server.Results.ClaimRouteResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fryti on 3/14/2018.
 */

public class ClaimRouteService {

    public ClaimRouteResult claimRoute(String playerName, int gameID, Route route ){
        TicketToRideGame currentGame = ModelRoot.instance().getAllGames().get(gameID);
        Player currentPlayer = new Player();
        for(int i=0; i<currentGame.getPlayers().size(); i++){
            if(currentGame.getPlayers().get(i).getUsername().equals(playerName))
                currentPlayer = currentGame.getPlayers().get(i);
        }

        //claim route for the player, remove the route from available routes, pay with train cards

        route.setOccupied(true);
        route.setOwner(currentPlayer);
        currentPlayer.addRoute(route);
        currentGame.removeRoute(route); //maybe need to iterate through to find the route instead???

        List<TrainCard> spentCards = new ArrayList<>();
        int numCardsStillOwed = route.getLength();


        //first remove color cards, and then wilds only if they run out
        for(int i=0; i<currentPlayer.getTrainCards().size(); i++){
            if(numCardsStillOwed==0) break;
            if(currentPlayer.getTrainCards().get(i).getColor().equals(route.getColor())){
                spentCards.add(currentPlayer.getTrainCards().get(i));
                currentPlayer.getTrainCards().remove(i);
                numCardsStillOwed--;
            }

        }
        for(int i=0; i<currentPlayer.getTrainCards().size(); i++){
            if(numCardsStillOwed==0) break;
            if(currentPlayer.getTrainCards().get(i).getColor().equals("wild")){
                spentCards.add(currentPlayer.getTrainCards().get(i));
                currentPlayer.getTrainCards().remove(i);
                numCardsStillOwed--;
            }
        }
        currentGame.getDiscardPile().addAll(spentCards);

        return new ClaimRouteResult(true,null,null, null, currentGame);

    }
}
