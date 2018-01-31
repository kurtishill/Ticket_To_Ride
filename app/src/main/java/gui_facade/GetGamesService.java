package gui_facade;

import com.example.server.Model.TicketToRideGame;

import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 1/30/18.
 */

public class GetGamesService {

    public static List<TicketToRideGame> getGames() {
        return ClientModelRoot.instance().getGames();
    }
}
