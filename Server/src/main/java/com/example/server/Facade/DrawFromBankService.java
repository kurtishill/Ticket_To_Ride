package com.example.server.Facade;

import com.example.server.Model.GameHistory;
import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Model.TrainCard;
import com.example.server.Results.DrawFromBankResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kurtis on 3/2/18.
 */

public class DrawFromBankService {

    public DrawFromBankResult draw(List<TrainCard> selectedCards, List<TrainCard> faceUpCards, List<TrainCard> trainCardDeck,
                                   ArrayList<TrainCard> discardPile, int gameId, String authToken) {
        TicketToRideGame game = ModelRoot.instance().GameExists(gameId);
        Player user = ModelRoot.instance().UserExists(authToken);

        for (int i = 0; i < selectedCards.size(); i++) {
            user.addTrainCard(selectedCards.get(i));
        }

        game.changeTurn();

        game.setFaceUpCards(faceUpCards);
        game.setDeckTrainCards(trainCardDeck);

        game.recycleTrainCardDeck();

        //added by Nelson for state pattern
        if(user.getState().equals("lastTurn")){
            user.setState("gameOver");
        }

        List<GameHistory> gameHistoryList = game.getGameHistoryList();
        GameHistory historyItem = new GameHistory(user.getUsername(), user.getColor(),
                "drew " + selectedCards.size() + " card(s) from the bank");
        gameHistoryList.add(historyItem);
        game.setGameHistoryList(gameHistoryList);

        ModelRoot.instance().updateGame(gameId, game);

        return new DrawFromBankResult(true, null, null, null, game);
    }
}
