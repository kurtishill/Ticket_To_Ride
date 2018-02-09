package com.example.server.Results;

import com.example.server.Model.Player;

import java.util.List;

/**
 * Created by fryti on 1/29/2018.
 */

public class RegisterResult extends Result {
    private Player player;


    public RegisterResult(boolean isSuccess, String errorMessage, List<ICommand> clientCommands, String errorType, Player player) {
        super(isSuccess, errorMessage, clientCommands, errorType, "RegisterResult");
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
