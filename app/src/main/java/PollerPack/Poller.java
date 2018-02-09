package PollerPack;

import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.GenericCommand;
import com.example.server.Results.GetGameListResult;
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
    private static Timer timer;

    public Poller() {
        timer = new Timer();
    }

    public void poll(){
        timer.schedule(new TimerTask() {
            public void run() {
                List<ICommand> commands = new ArrayList<>();
                commands.add(pollGameList());

                for (int i = 0; i < commands.size(); i++) {
                    if (commands.get(i) != null)
                        commands.get(i).execute();
                }
            }
        }, 0, 7000);
    }

    private ICommand pollGameList() {
        List<Object> data = new ArrayList<>();
        data.add("GetGameList");
        Result result = ClientCommunicator.instance().send("/command", data, null);
        GetGameListResult getGameListResult = (GetGameListResult) result;
        List<TicketToRideGame> games = getGameListResult.getGames();
        if (games != null) {
            return new GenericCommand("client_facade.ClientFacade", "UpdateGameList",
                    new Class<?>[]{ArrayList.class}, new Object[]{games});
        }
        else
            return null;
    }
}
