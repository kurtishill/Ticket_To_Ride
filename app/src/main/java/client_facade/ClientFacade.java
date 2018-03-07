package client_facade;

import com.example.server.Model.DestinationCard;
import com.example.server.Model.Player;
import com.example.server.Model.ChatMessage;
import com.example.server.Model.TicketToRideGame;

import java.util.ArrayList;
import java.util.List;

import gui_facade.DrawDestinationTicketsService;
import gui_facade.SelectDestinationTicketsService;

/**
 * Created by Clayton Kings on 2/2/2018.
 */

public class ClientFacade {

    private UpdateGameListService mUpdateGameListService;
    private UpdateGameChatService mUpdateGameChatService;
    private DrawDestinationTicketsService mDrawDestinationTickesService;
    private SelectDestinationTicketsService mSelectDestinationTicketsService;

    public ClientFacade() {
        mUpdateGameListService = new UpdateGameListService();
        mUpdateGameChatService = new UpdateGameChatService();
        mDrawDestinationTickesService = new DrawDestinationTicketsService();
        mSelectDestinationTicketsService = new SelectDestinationTicketsService();
    }

    public Object UpdateGameList(ArrayList<TicketToRideGame> games) {
        mUpdateGameListService.updateGameList(games);
        return null;
    }

    public Object UpdateGameChat(ArrayList<ChatMessage> chat) {
        mUpdateGameChatService.updateChat(chat);
        return null;
    }

    public Object DrawDestinationTickets() {
        mDrawDestinationTickesService.drawCards();
        return null;
    }

    public Object SelectDestinationTickets(Player player, List<DestinationCard> selectedCards, List<DestinationCard> discardedCards) {
        mSelectDestinationTicketsService.selectCards(player, selectedCards, discardedCards);
        return null;
    }

}

