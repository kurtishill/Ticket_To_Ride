package PollerPack;

import com.example.server.Model.ChatMessage;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.ChatResult;
import com.example.server.Results.GenericCommand;
import com.example.server.Results.GetGameListResult;
import com.example.server.Results.ICommand;
import com.example.server.Results.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Network.ClientCommunicator;
import client_facade.ToggleGUIUsability;
import client_model.ClientModelRoot;

//import client_facade.ClientFacade;

/**
 * Created by Clayton Kings on 2/2/2018.
 */

public class Poller {
    private Timer timer;
    private List<ICommand> commands;
    private String key;
    private boolean isServerDown;

    public Poller(String key) {
        timer = new Timer();
        commands = new ArrayList<>();
        this.key = key;
        isServerDown = false;
    }

    public void poll(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                commands = new ArrayList<>();
                commands.add(pollGameList());
                commands.add(pollChat());
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
        commands.clear();
    }

    // this also grabs the updated game when a person draws from the bank
    // so an additional poll for someone drawing from the bank isn't necessary
    private ICommand pollGameList() {
        List<Object> data = new ArrayList<>();
        data.add("GetGameList");
        Result result = ClientCommunicator.instance().send("/command", data, key);
        GetGameListResult getGameListResult;
        if (result.isSuccess()) {
            getGameListResult = (GetGameListResult) result;
            if (isServerDown) {
                ToggleGUIUsability.toggle(false);
            }
            isServerDown = false;
        }
        else {
            if (result.getErrorMessage().equals("Can't connect to server")) {
                if (!isServerDown) {
                    ToggleGUIUsability.toggle(true);
                }
                isServerDown = true;
            }
            return null;
        }
        List<TicketToRideGame> games = getGameListResult.getGames();
        if (games != null) {
            return new GenericCommand("client_facade.ClientFacade", "UpdateGameList",
                    new Class<?>[]{ArrayList.class}, new Object[]{games});
        }
        else
            return null;
    }
    private ICommand pollChat() {
        if(ClientModelRoot.instance().getCurrGame() == null){
            return null;
        }
        List<Object> data = new ArrayList<>();
        data.add("GetChat");
        data.add(ClientModelRoot.instance().getCurrGame().getGameID());
        data.add(ClientModelRoot.instance().getUser().getUsername());
        Result result = ClientCommunicator.instance().send("/command", data, key);
        ChatResult chatResult;
        if (result.isSuccess()) {
            chatResult = (ChatResult) result;
            if (isServerDown) {
                ToggleGUIUsability.toggle(false);
            }
            isServerDown = false;
        }
        else {
            if (result.getErrorMessage().equals("Can't connect to server")) {
                if (!isServerDown) {
                    ToggleGUIUsability.toggle(true);
                }
                isServerDown = true;
            }
            return null;
        }
        List<ChatMessage> chat = chatResult.getChat();
        if (chat != null) {
            return new GenericCommand("client_facade.ClientFacade", "UpdateGameChat",
                    new Class<?>[]{ArrayList.class}, new Object[]{chat});
        }
        else
            return null;
    }
}
