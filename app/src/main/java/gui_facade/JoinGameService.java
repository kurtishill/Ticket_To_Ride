package gui_facade;

import com.example.server.Model.TicketToRideGame;

import java.util.List;

import client_model.ClientModelRoot;

/**
 * Created by HillcollegeMac on 1/30/18.
 */

public class JoinGameService {

    public static void joinGame(TicketToRideGame game) {
        ClientModelRoot.instance().setCurrGame(game);
        List<TicketToRideGame> games = ClientModelRoot.instance().getGamesList();
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGameID() == game.getGameID())
                games.set(i, game);
        }
        ClientModelRoot.instance().setGames(games);
        /*Map<String, Player> players = ClientModelRoot.instance().getPlayers();
        players.put(game.getPlayers().get(game.getPlayers().size() - 1).getID(),
                game.getPlayers().get(game.getPlayers().size() - 1));
        ClientModelRoot.instance().setPlayers(players);*/
    }
}
