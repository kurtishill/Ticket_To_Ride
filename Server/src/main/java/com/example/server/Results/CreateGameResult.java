package com.example.server.Results;

import java.util.List;

/**
 * Created by fryti on 1/29/2018.
 */

public class CreateGameResult extends Result {

    public CreateGameResult(boolean isSuccess, String errorMessage, List<GenericCommand> clientCommands, String errorType) {
        super(isSuccess, errorMessage, clientCommands, errorType, "CreateGameResult");
    }

}
