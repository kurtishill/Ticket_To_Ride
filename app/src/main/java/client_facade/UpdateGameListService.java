package client_facade;

import com.example.server.Model.TicketToRideGame;

import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 2/9/18.
 */

public class UpdateGameListService {

    public void updateGameList(List<TicketToRideGame> games) {
        ClientModelRoot.instance().setGames(games);
    }
}
