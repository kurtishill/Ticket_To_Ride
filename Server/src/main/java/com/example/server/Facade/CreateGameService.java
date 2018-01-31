package com.example.server.Facade;

import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.CreateGameResult;

/**
 * Created by ckingsbu on 1/30/18.
 */

public class CreateGameService {
    public CreateGameResult CreateGame(String username){
        Player currentPlayer = ModelRoot.instance().UserExists(username);
        if(currentPlayer != null){
             ModelRoot.instance().addGame(ModelRoot.instance().getListGames().size(), new TicketToRideGame(currentPlayer));
            return new CreateGameResult(true, null, null, null );
        }
        else{
            return new CreateGameResult(false, "User Does Not Exist",  null, "invalidUser");
        }
    }
}
