package com.example.server.Results;

import java.util.List;

/**
 * Created by fryti on 1/29/2018.
 */

public class JoinGameResult extends Result {
    public JoinGameResult(boolean isSuccess, String errorMessage, List<GenericCommand> clientCommands, String errorType) {
        super(isSuccess, errorMessage, clientCommands, errorType, "JoinGameResult");
    }
}
