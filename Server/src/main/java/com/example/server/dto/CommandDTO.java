package com.example.server.dto;

import com.example.server.Results.ICommand;

/**
 * Created by kurtishill on 4/9/18.
 */

public class CommandDTO {

    private int id;

    private ICommand command;

    private int gameId;

    public CommandDTO() {}

    public CommandDTO(int id, ICommand command, int gameId) {
        this.id = id;
        this.command = command;
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public ICommand getCommand() {
        return command;
    }

    public int getGameId() {
        return gameId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
