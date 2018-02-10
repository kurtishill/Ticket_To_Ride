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
    private List<ICommand> commands;

    public Poller() {
        timer = new Timer();
        commands = new ArrayList<>();
    }

    public void poll(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                commands = new ArrayList<>();
                commands.add(pollGameList());

                runCommands();

                //cancel();

                /*for (int i = 0; i < commands.size(); i++) {
                    if (commands.get(i) != null)
                        commands.get(i).execute();
                }*/
                //run();
            }
        }, 7000, 5000);
    }

    private void runCommands() {
        //timer.cancel();
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i) != null)
                commands.get(i).execute();
        }
        //timer = new Timer();
        //poll();
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
