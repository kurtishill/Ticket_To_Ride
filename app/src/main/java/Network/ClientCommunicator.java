package Network;

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



    public Result send(String handlerContext, List<Object> data)
    {
        try {
            String wholeUrl = "http://" + ServerProxy.getServerHost() + ":" +
                    ServerProxy.getServerPort() + handlerContext;
            URL url = new URL(wholeUrl);

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body
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
            return (Result) Serializer.decode(resp, Result.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, "Error", null, "IOException");
    }

}
