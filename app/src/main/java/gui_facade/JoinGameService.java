package gui_facade;

import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;

import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 1/30/18.
 */

public class JoinGameService {

    public static void joinGame(TicketToRideGame game) {
        Player user = game.getPlayers().get(game.getPlayers().size() - 1);
        AddUserService.addUser(user);
        ClientModelRoot.instance().setCurrGame(game);
        List<TicketToRideGame> games = ClientModelRoot.instance().getGamesList();
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameID() == game.getGameID())
                games.set(i, game);
        }
        ClientModelRoot.instance().setGames(games);
    }
}
