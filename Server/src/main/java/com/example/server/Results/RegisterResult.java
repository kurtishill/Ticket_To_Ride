package com.example.server.Results;

import java.util.List;

/**
 * Created by fryti on 1/29/2018.
 */

public class RegisterResult extends Result {
    private String authToken;
 origin/client_views_presenters_guifacade_clientmodel

    public RegisterResult(boolean isSuccess, String errorMessage, List<GenericCommand> clientCommands, String errorType, String authToken) {
        super(isSuccess, errorMessage, clientCommands, errorType);
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}
