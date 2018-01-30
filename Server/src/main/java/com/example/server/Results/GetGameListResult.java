package com.example.server.Results;

import java.util.List;

/**
 * Created by fryti on 1/29/2018.
 */

public class GetGameListResult extends Result {
    private List<Game> games;

    public GetGameListResult(boolean isSuccess, String errorMessage, List<GenericCommand> clientCommands, String errorType, List<Game> games) {
        super(isSuccess, errorMessage, clientCommands, errorType);
        this.games=games;
    }

    public List<Game> getGames(){
        return games;
    }
}
