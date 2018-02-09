package com.example.server.Results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fryti on 1/29/2018.
 */

public class Result {
    private boolean isSuccess;
    private String errorMessage;
    private List<ICommand> clientCommands;
    private String errorType;
    private String type;

    public Result() {
        isSuccess = false;
        errorMessage = null;
        clientCommands = new ArrayList<>();
        errorType = null;
        type = null;
    }

    public Result(boolean isSuccess, String errorMessage, List<ICommand> clientCommands, String errorType, String type) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.clientCommands = clientCommands;
        this.errorType = errorType;
        this.type = type;
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<ICommand> getClientCommands() {

        return clientCommands;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getType() {
        return type;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setClientCommands(List<ICommand> clientCommands) {
        this.clientCommands = clientCommands;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public void setType(String type) {
        this.type = type;
    }
}
