package com.example.server;

import com.example.server.Model.ModelRoot;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.ClientCommand;
import com.example.server.Results.GenericCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HillcollegeMac on 2/9/18.
 */

public class ClientCommandManager {

    private static ClientCommandManager _instance;
    private List<String> commands;

    public static ClientCommandManager instance() {
        if (_instance == null)
            _instance = new ClientCommandManager();
        return _instance;
    }

    private ClientCommandManager() {
        commands = new ArrayList<>();
    }

    public void setCommands(List<String> list) {
        commands = list;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public List<String> getCommandList() {
        return commands;
    }

    public ClientCommand makeClientCommandList(String typeOfCommand) {
        ClientCommand clientCommand = new ClientCommand("UpdateGameList");
        if (typeOfCommand.equals("UpdateGameList")) {
            clientCommand.addData((ModelRoot.instance().getListGames()));
            return clientCommand;
        }
        return null;
    }
    public GenericCommand GetGameList(List<TicketToRideGame> list) {
        return new GenericCommand("client_facade.ClientFacade", "UpdateGameList",
                new Class<?>[]{ArrayList.class}, new Object[]{list});
    }
}
