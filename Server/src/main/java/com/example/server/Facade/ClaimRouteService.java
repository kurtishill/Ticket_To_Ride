package com.example.server.Facade;

import com.example.server.Model.GameHistory;
import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.Route;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Model.TrainCard;
import com.example.server.Results.ClaimRouteResult;
import com.example.server.Results.DrawFromBankResult;

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
        route.setOwner(currentPlayer.getUsername());
        currentPlayer.addRoute(route);
        currentPlayer.setNumTrainCars(currentPlayer.getNumTrainCars()-route.getLength());
        currentGame.removeRoute(route);

        List<TrainCard> spentCards = new ArrayList<>();
        int numCardsStillOwed = route.getLength();


        //first remove color cards, and then wilds only if they run out
        for(int i=0; i<currentPlayer.getTrainCards().size(); i++){
            if(numCardsStillOwed==0) break;
            if(currentPlayer.getTrainCards().get(i).getColor().equals(route.getColor())){
                spentCards.add(currentPlayer.getTrainCards().get(i));
                currentPlayer.getTrainCards().remove(i);
                i--;
                numCardsStillOwed--;
            }
        }

        for(int i=0; i<currentPlayer.getTrainCards().size(); i++){
            if(numCardsStillOwed==0) break;
            if(currentPlayer.getTrainCards().get(i).getColor().equals("wild")){
                spentCards.add(currentPlayer.getTrainCards().get(i));
                currentPlayer.getTrainCards().remove(i);
                i--;
                numCardsStillOwed--;
            }
        }

        currentGame.getDiscardPile().addAll(spentCards);
        currentGame.recycleTrainCardDeck();
        currentGame.changeTurn();

        List<GameHistory> gameHistoryList = currentGame.getGameHistoryList();
        GameHistory historyItem = new GameHistory(currentPlayer.getUsername(), currentPlayer.getColor(),
                "claimed a route!");
        gameHistoryList.add(historyItem);
        currentGame.setGameHistoryList(gameHistoryList);

        return new ClaimRouteResult(true,null,null, null, currentGame);

    }
}
