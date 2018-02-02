package Network;

import com.example.server.Model.Player;
import com.example.server.Results.CreateGameResult;
import com.example.server.Results.GetGameListResult;
import com.example.server.Results.JoinGameResult;
import com.example.server.Results.LoginResult;
import com.example.server.Results.RegisterResult;
import com.example.server.Results.Result;

/**
 * Created by tnels on 1/30/2018.
 */

public class ServerProxy  {

    private String serverHost;
    private String serverPort;

    public Result command(String functionName)
    {       //TODO add correct parameters
        return null;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }
}
