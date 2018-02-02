package Network;

import com.example.server.Results.GenericCommand;
import com.example.server.Results.Result;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tnels on 1/30/2018.
 */

public class ClientCommunicator {


    public ClientCommunicator() {}



    public Result send(String wholeUrl, GenericCommand command)
    {
        try {
            URL url = new URL(wholeUrl);

            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body

            http.connect();
            String reqData = Serializer.encode(command);

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
            Result result = Serializer.decode(resp);
            return result;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, "Error", null, "IOException");
    }

}
