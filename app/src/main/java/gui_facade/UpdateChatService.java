package gui_facade;

import com.example.server.ChatMessage;
import com.example.server.Model.TicketToRideGame;

import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by Clayton Kings on 2/17/2018.
 */

public class UpdateChatService {
    public static void UpdateChatService(ChatMessage message) {
        List<ChatMessage> list = ClientModelRoot.instance().getChat();
        list.add(message);
        ClientModelRoot.instance().setChat(list);
    }
}
