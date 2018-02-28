package com.example.server.Results;

import com.example.server.ChatMessage;
import com.example.server.Model.Player;

import java.util.List;

/**
 * Created by Clayton Kings on 2/17/2018.
 */

public class ChatResult extends Result {
    private List<ChatMessage> chat;

    public ChatResult(boolean isSuccess, String errorMessage, List<ClientCommand> clientCommands, String errorType, List<ChatMessage> chat) {
        super(isSuccess, errorMessage, clientCommands, errorType, "ChatResult");
        this.chat = chat;
    }

    public List<ChatMessage> getChat() {
        return chat;
    }
}
