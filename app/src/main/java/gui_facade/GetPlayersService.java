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
    public static Player getCurrentPlayer(){
        Player currentPlayer = new Player();
        for(int i=0; i<ClientModelRoot.instance().getCurrGame().getPlayers().size(); i++) {
            if(ClientModelRoot.instance().getCurrGame().getPlayers().get(i).getUsername().equals( ClientModelRoot.instance().getUser().getUsername()))
                currentPlayer = ClientModelRoot.instance().getCurrGame().getPlayers().get(i);
        }
        return currentPlayer;
    }
}
