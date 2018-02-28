package Network;

import com.example.server.Results.CreateGameResult;
import com.example.server.Results.DrawDestinationTicketsResult;
import com.example.server.Results.GetGameListResult;
import com.example.server.Results.JoinGameResult;
import com.example.server.Results.LoginResult;
import com.example.server.Results.RegisterResult;
import com.example.server.Results.Result;
import com.example.server.Serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by tnels on 1/30/2018.
 */

public class ClientCommunicator {

    private static ClientCommunicator _instance;

    public static ClientCommunicator instance() {
        if (_instance == null)
            _instance = new ClientCommunicator();
        return _instance;
    }

    private ClientCommunicator() {}



    public Result send(String handlerContext, List<Object> data, String authToken)
    {
        try {
            String wholeUrl = "http://" + ServerProxy.getServerHost() + ":" +
                    ServerProxy.getServerPort() + handlerContext;
            URL url = new URL(wholeUrl);

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body
            if (authToken != null)
                http.setRequestProperty("Authorization", authToken);
            http.connect();

            String reqData = Serializer.encode(data);

            OutputStream reqBody = http.getOutputStream();
            Serializer.writeString(reqData, reqBody);
            reqBody.close();

            InputStream respBody;
            if (http.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                respBody = http.getInputStream();
                System.out.println("Sent Command Successfully");
            }
            else
            {
                respBody = http.getErrorStream();
                System.out.println("ERROR: " + http.getResponseMessage());
            }
            String resp = Serializer.readString(respBody);
            if (resp.contains("RegisterResult"))
                return (Result) Serializer.decode(resp, RegisterResult.class);
            else if (resp.contains("LoginResult"))
                return (Result) Serializer.decode(resp, LoginResult.class);
            else if (resp.contains("GetGameList"))
                return (Result) Serializer.decode(resp, GetGameListResult.class);
            else if (resp.contains("JoinGameResult"))
                return (Result) Serializer.decode(resp, JoinGameResult.class);
            else if (resp.contains("CreateGameResult"))
                return (Result) Serializer.decode(resp, CreateGameResult.class);
            else if (resp.contains("DrawDestinationTicketsResult"))
                return (Result) Serializer.decode(resp, DrawDestinationTicketsResult.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, "Error", null, "IOException", null);
    }
}
