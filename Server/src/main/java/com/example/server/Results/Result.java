package com.example.server.Results;

import java.util.List;

/**
 * Created by fryti on 1/29/2018.
 */

public abstract class Result {
    private boolean isSuccess;
    private String errorMessage;
    private List<GenericCommand> clientCommands;
    private String errorType;

    public Result(boolean isSuccess, String errorMessage, List<GenericCommand> clientCommands, String errorType) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.clientCommands = clientCommands;
        this.errorType = errorType;
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<GenericCommand> getClientCommands() {
        return clientCommands;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setClientCommands(List<GenericCommand> clientCommands) {
        this.clientCommands = clientCommands;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}
