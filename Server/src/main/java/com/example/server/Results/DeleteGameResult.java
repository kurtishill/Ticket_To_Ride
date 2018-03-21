package com.example.server.Results;

import java.util.List;

/**
 * Created by kurtishill on 3/14/18.
 */

public class DeleteGameResult extends Result {

    public DeleteGameResult(boolean isSuccess, String errorMessage, List<ClientCommand> clientCommands, String errorType) {
        super(isSuccess, errorMessage, clientCommands, errorType, "DeleteGameResult");
    }
}
