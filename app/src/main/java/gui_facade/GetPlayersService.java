package gui_facade;

import com.example.server.Model.Player;

import java.util.Map;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 1/30/18.
 */

public class GetPlayersService {

    public static Map<String, Player> getPlayers() {
        return ClientModelRoot.instance().getPlayers();
    }
}
