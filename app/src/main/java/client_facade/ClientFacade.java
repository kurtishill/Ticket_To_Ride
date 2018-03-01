package client_facade;

import com.example.server.Model.ChatMessage;
import com.example.server.Model.TicketToRideGame;

import java.util.ArrayList;

/**
 * Created by Clayton Kings on 2/2/2018.
 */

public class ClientFacade {

    private UpdateGameListService mUpdateGameListService;
    private UpdateGameChatService mUpdateGameChatService;
    public ClientFacade() {
        mUpdateGameListService = new UpdateGameListService();
        mUpdateGameChatService = new UpdateGameChatService();
    }

    public Object UpdateGameList(ArrayList<TicketToRideGame> games) {
        mUpdateGameListService.updateGameList(games);
        return null;
    }
    public Object UpdateGameChat(ArrayList<ChatMessage> chat) {
        mUpdateGameChatService.updateChat(chat);
        return null;
    }
}

