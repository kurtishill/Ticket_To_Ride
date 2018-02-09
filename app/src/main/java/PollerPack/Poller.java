package PollerPack;

import com.example.server.Results.ICommand;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Network.ClientCommunicator;

//import client_facade.ClientFacade;

/**
 * Created by Clayton Kings on 2/2/2018.
 */

public class Poller {
    void Poll(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Result gameListResult = pollGameList();
                List<ICommand> commands = gameListResult.getClientCommands();
                for (int i = 0; i < commands.size(); i++) {
                    commands.get(i).execute();
                }
            }
        }, 0, 3000);
    }

    private Result pollGameList() {
        List<Object> data = new ArrayList<>();
        data.add("GetGameList");
        return ClientCommunicator.instance().send("/command", data, null);
    }
}
