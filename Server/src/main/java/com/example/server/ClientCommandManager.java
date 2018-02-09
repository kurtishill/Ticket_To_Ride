package com.example.server;

import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.GenericCommand;
import com.example.server.Results.ICommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HillcollegeMac on 2/9/18.
 */

public class ClientCommandManager {

    private static ClientCommandManager _instance;
    private List<ICommand> commands;

    public static ClientCommandManager instance() {
        if (_instance == null)
            _instance = new ClientCommandManager();
        return _instance;
    }

    private ClientCommandManager() {
        commands = new ArrayList<>();
    }

    public void addCommand(ICommand command) {
        commands.add(command);
    }

    public List<ICommand> getCommandList() {
        return commands;
    }

    public GenericCommand GetGameList(List<TicketToRideGame> list) {
        return new GenericCommand("client_facade.ClientFacade", "UpdateGameList",
                new Class<?>[]{ArrayList.class}, new Object[]{list});
    }
}
