package com.example.server.Facade;

import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.CreateGameResult;

/**
 * Created by ckingsbu on 1/30/18.
 */

public class CreateGameService {
    public CreateGameResult CreateGame(String gameName, Integer maxNumPlayers, String playerColor, String authToken) {
        Player currentPlayer = ModelRoot.instance().UserExists(authToken);
        currentPlayer.setColor(playerColor);
        ModelRoot.instance().addPlayer(authToken, currentPlayer);
        boolean gameNameDoesNotExist = true;
        for (int i = 0; i < ModelRoot.instance().getListGames().size(); i++) {
            if (gameName.equals(ModelRoot.instance().getListGames().get(i).getName()))
                gameNameDoesNotExist = false;
        }
        if (gameNameDoesNotExist) {

            TicketToRideGame game = new TicketToRideGame(currentPlayer);

            game.setName(gameName);
            game.setMaxNumPlayers(maxNumPlayers);
            game.setGameID(ModelRoot.instance().getListGames().size() + 1);
            game.getAvailableColors().remove(playerColor); //added 2/9 by Brandon
            ModelRoot.instance().addGame(game.getGameID(), game);
            return new CreateGameResult(true, null, null, null, game);
        }
        return new CreateGameResult(false, "Game name already taken", null,
                "Game already made", null);
    }
}
