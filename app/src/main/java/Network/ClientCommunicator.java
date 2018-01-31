package Network;

import com.example.server.Results.Result;
import com.google.gson.Gson;

/**
 * Created by tnels on 1/30/2018.
 */

public class ClientCommunicator {


    public ClientCommunicator() {}

    public String encode(String info)
    {
        Gson gson = new Gson();
        return gson.toJson(info, info.getClass());
    }

    public Result decode(String json)
    {
        Gson gson = new Gson();
        return (Result) gson.fromJson(json, Result.class);
    }

    public Result send(String wholeUrl)
    {


    }

}
