package com.example.server;

import com.example.server.Model.ChatMessage;
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
            if (!entry.getValue().getUsername().equals(player.getUsername())) {
                if (commands.get(entry.getValue().getUsername()) == null)
                    set = new HashSet<>();
                else
                    set = commands.get(entry.getValue().getUsername());

                set.add(command);
                commands.put(entry.getValue().getUsername(), set);
            }
        }
    }
    public void addGameCommand(int gameID, String command) {
        TicketToRideGame game = ModelRoot.instance().getAllGames().get(gameID);
        List<Player> players =  game.getPlayers();
        Set<String> set;
        for (int i = 0; i < players.size(); i++) {

            if (commands.get(players.get(i).getUsername()) == null)
                set = new HashSet<>();
            else
                set = commands.get(players.get(i).getUsername());

            set.add(command);
            commands.put(players.get(i).getUsername(), set);

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
    public GenericCommand GetChat(List<ChatMessage> chat) {
        return new GenericCommand("client_facade.ClientFacade", "UpdateGameChatService",
                new Class<?>[]{ArrayList.class}, new Object[]{chat});
    }
}
