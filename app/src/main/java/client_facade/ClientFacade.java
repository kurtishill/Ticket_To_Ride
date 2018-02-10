package client_facade;

import com.example.server.Model.TicketToRideGame;

import java.util.ArrayList;

/**
 * Created by Clayton Kings on 2/2/2018.
 */

public class ClientFacade {

    private UpdateGameListService mUpdateGameListService;

    public ClientFacade() {
        mUpdateGameListService = new UpdateGameListService();
    }

    public Object UpdateGameList(ArrayList<TicketToRideGame> games) {
        mUpdateGameListService.updateGameList(games);
        return null;
    }
}

