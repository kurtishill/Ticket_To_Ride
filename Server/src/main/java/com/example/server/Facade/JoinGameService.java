package com.example.server.Facade;

import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.JoinGameResult;

/**
 * Created by ckingsbu on 1/30/18.
 */

public class JoinGameService {
    public JoinGameResult JoinGame(Integer gameId, String authToken){
        TicketToRideGame currentGame = ModelRoot.instance().GameExists(gameId);
        Player currentPlayer = ModelRoot.instance().getAllPlayers().get(authToken);
        if(currentPlayer != null ){
            if(currentGame != null){
                if(currentGame.getMaxNumPlayers() == currentGame.getPlayers().size()){
                    return new JoinGameResult(false, "Game Already Filled", null, "fullGame", null);
                }
                else{
                    currentGame.addPlayer(currentPlayer);
                    return new JoinGameResult(true, null, null, null, currentGame);
                }
            }
            else{
                return new JoinGameResult(false, "Game Does Not Exist", null, "invalidGame", null);
            }
        }
        else{
            return new JoinGameResult(false, "User Does Not Exist", null, "userInvalid", null);
        }
    }
}
