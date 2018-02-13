package com.example.server;

import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.ClientCommand;
import com.example.server.Results.GenericCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by HillcollegeMac on 2/9/18.
 */

public class ClientCommandManager {

    private static ClientCommandManager _instance;
    private Map<String, Set<String>> commands;

    public static ClientCommandManager instance() {
        if (_instance == null)
            _instance = new ClientCommandManager();
        return _instance;
    }

    private ClientCommandManager() {

    }

    {
        commands = new HashMap<>();
        Set<String> initSet = new HashSet<>();
        initSet.add("UpdateGameList");
        commands.put("sign-in", initSet);
    }

    public void setCommands(Map<String, Set<String>> commands) {
        this.commands = commands;
    }

    public void addCommand(String authToken, String command) {
        Player player = ModelRoot.instance().getAllPlayers().get(authToken);
        Set<String> set;
        for (Map.Entry<String, Player> entry : ModelRoot.instance().getAllPlayers().entrySet()) {
            if (command.equals("UpdateGameListJoin")) {
                if (commands.get(entry.getKey()) == null)
                    set = new HashSet<>();
                else
                    set = commands.get(player.getUsername());

                set.add("UpdateGameList");
                commands.put(player.getUsername(), set);
            }
            else {
                if (!entry.getKey().equals(player.getUsername())) {
                    if (commands.get(entry.getKey()) == null)
                        set = new HashSet<>();
                    else
                        set = commands.get(player.getUsername());

                    set.add(command);
                    commands.put(player.getUsername(), set);
                }
            }
        }
    }

    public Map<String, Set<String>> getCommands() {
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
