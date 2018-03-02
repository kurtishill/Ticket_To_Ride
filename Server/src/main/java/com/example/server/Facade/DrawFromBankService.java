package com.example.server.Facade;

import com.example.server.Model.GameHistory;
import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Model.TrainCard;
import com.example.server.Results.DrawFromBankResult;

import java.util.List;

/**
 * Created by kurtis on 3/2/18.
 */

public class DrawFromBankService {

    public DrawFromBankResult draw(List<TrainCard> selectedCards, List<TrainCard> faceUpCards, List<TrainCard> trainCardDeck,
                                   int gameId, String authToken) {
        TicketToRideGame game = ModelRoot.instance().GameExists(gameId);
        Player user = ModelRoot.instance().UserExists(authToken);
        user.addTrainCard(selectedCards.get(0));
        user.addTrainCard(selectedCards.get(1));
        game.changeTurn();
        game.setFaceUpCards(faceUpCards);
        game.setDeckTrainCards(trainCardDeck);

        List<GameHistory> gameHistoryList = game.getGameHistoryList();
        GameHistory historyItem = new GameHistory(user.getUsername(), user.getColor(),
                "drew two cards from the bank");
        gameHistoryList.add(historyItem);
        game.setGameHistoryList(gameHistoryList);

        ModelRoot.instance().updateGame(gameId, game);

        return new DrawFromBankResult(true, null, null, null, game);
    }
}
