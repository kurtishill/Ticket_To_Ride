package client_facade;

import com.example.server.Model.TicketToRideGame;

import java.util.ArrayList;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 2/9/18.
 */

public class UpdateGameListService {

    public UpdateGameListService() {

    }

    public void updateGameList(ArrayList<TicketToRideGame> games) {
        ClientModelRoot.instance().setGames(games);
    }
}
