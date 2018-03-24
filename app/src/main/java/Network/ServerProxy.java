package Network;

import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tnels on 1/30/2018.
 */

public class ServerProxy  {
    private static ServerProxy instance = null;
    private String serverHost;
    private String serverPort;

    private ServerProxy() {
        serverHost = null;
       
        serverPort = "8080";
    }

    public static ServerProxy getInstance() {
        if(instance == null) {
            instance = new ServerProxy();
        }
        return instance;
    }

    public Result command(String functionName, List<Object> data, String authToken)
    {
        List<Object> commandValues = new ArrayList<>();
        commandValues.add(functionName);
        commandValues.addAll(data);
        return ClientCommunicator.instance().send("/command", commandValues, authToken);
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String host) {
        serverHost = host;
    }
}
