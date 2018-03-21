package Network;

import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tnels on 1/30/2018.
 */

public class ServerProxy  {
    private static ServerProxy instance = null;
    private static String serverHost;
    private static String serverPort;

    private ServerProxy() {
        serverHost = "10.24.65.103";
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

    public static String getServerPort() {
        return serverPort;
    }

    public static String getServerHost() {
        return serverHost;
    }
}
