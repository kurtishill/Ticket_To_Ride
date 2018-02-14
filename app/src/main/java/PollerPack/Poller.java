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
    private Timer timer;
    private List<ICommand> commands;
    private String key;

    public Poller(String key) {
        timer = new Timer();
        commands = new ArrayList<>();
        this.key = key;
    }

    public void poll(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                commands = new ArrayList<>();
                commands.add(pollGameList());

                runCommands();
            }
        }, 0, 1000);
    }

    public void signInPoll() {
        commands = new ArrayList<>();
        commands.add(pollGameList());

        runCommands();
    }

    public void runCommands() {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i) != null)
                commands.get(i).execute();
        }
    }

    private ICommand pollGameList() {
        List<Object> data = new ArrayList<>();
        data.add("GetGameList");
        Result result = ClientCommunicator.instance().send("/command", data, key);
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
