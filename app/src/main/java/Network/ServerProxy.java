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

    private ServerProxy(String serverHost, String serverPort) {
        setServerHost(serverHost);
        setServerPort(serverPort);
    }

    public static ServerProxy getInstance(String serverHost, String serverPort) {
        if(instance == null) {
            instance = new ServerProxy(serverHost, serverPort);
        }
        return instance;
    }

    public Result command(String functionName, List<Object> data)
    {
        List<Object> commandValues = new ArrayList<>();
        commandValues.add(functionName);
        commandValues.addAll(data);
        return ClientCommunicator.instance().send("/command", commandValues);
    }

    public static String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public static String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }
}
