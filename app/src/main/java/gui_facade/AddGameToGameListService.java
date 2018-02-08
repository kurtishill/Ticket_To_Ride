package gui_facade;

import com.example.server.Model.TicketToRideGame;

import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 1/30/18.
 */

public class AddGameToGameListService {

    public static void addGameToGameList(TicketToRideGame game) {
        List<TicketToRideGame> list = ClientModelRoot.instance().getGamesList();
        list.add(game);
        ClientModelRoot.instance().setGames(list);
    }
}
