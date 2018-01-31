package gui_facade;

import com.example.server.Model.TicketToRideGame;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 1/30/18.
 */

public class JoinGameService {

    public static void joinGame(TicketToRideGame game) {
        ClientModelRoot.instance().setCurrGame(game);
        ClientModelRoot.instance().addUserToCurrGame();
    }
}
