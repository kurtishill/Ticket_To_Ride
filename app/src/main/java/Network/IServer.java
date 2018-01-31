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

public interface IServer {

    public LoginResult login(String username, String password);

    public RegisterResult register(String username, String password);

    public CreateGameResult createNewGame(Player startingPlayer, int numPlayers);

    public JoinGameResult joinGame(Player player);

    public GetGameListResult getGameList();

}
