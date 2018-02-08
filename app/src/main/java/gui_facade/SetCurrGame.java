package gui_facade;

import com.example.server.Model.TicketToRideGame;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 2/7/18.
 */

public class SetCurrGame {

    public static void setCurrGame(TicketToRideGame game) {
        ClientModelRoot.instance().setCurrGame(game);
    }
}
