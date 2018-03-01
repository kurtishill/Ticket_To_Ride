package client_facade;

import com.example.server.Model.ChatMessage;

import java.util.ArrayList;

import client_model.ClientModelRoot;

/**
 * Created by Clayton Kings on 2/17/2018.
 */

public class UpdateGameChatService {
    public void updateChat(ArrayList<ChatMessage> chat) {
        ClientModelRoot.instance().setChat(chat);
    }
}
