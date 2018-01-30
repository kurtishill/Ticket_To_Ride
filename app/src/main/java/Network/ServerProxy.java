package Network;

import com.example.server.Model.Player;
import com.example.server.Results.CreateGameResult;
import com.example.server.Results.GetGameListResult;
import com.example.server.Results.JoinGameResult;
import com.example.server.Results.LoginResult;
import com.example.server.Results.RegisterResult;

/**
 * Created by tnels on 1/30/2018.
 */

public class ServerProxy implements IServer {

    @Override
    public LoginResult login(String username, String password) {
        return null;
    }

    @Override
    public RegisterResult register(String username, String password) {
        return null;
    }

    @Override
    public CreateGameResult createNewGame(Player startingPlayer, int numPlayers) {
        return null;
    }

    @Override
    public JoinGameResult joinGame(Player player) {
        return null;
    }

    @Override
    public GetGameListResult getGameList() {
        return null;
    }
}
