package com.example.server.Facade;

import com.example.server.ClientCommandManager;
import com.example.server.Model.ModelRoot;
import com.example.server.Results.GetGameListResult;

import java.util.Map;
import java.util.Set;

/**
 * Created by ckingsbu on 1/30/18.
 */

public class GetGameListService {
    public GetGameListResult GetGameList(String authToken) {
        Map<String, Set<String>> commandMap = ClientCommandManager.instance().getCommands();
        Set<String> commands = commandMap.get(authToken);
        if (commands != null) {
            if (commands.contains("UpdateGameList")) {
                if (!authToken.equals("sign-in"))
                    commands.remove("UpdateGameList");
                commandMap.put(authToken, commands);
                ClientCommandManager.instance().setCommands(commandMap);
                return new GetGameListResult(true, null, null, null,
                        ModelRoot.instance().getListGames());
            }
        }
        return new GetGameListResult(true, null, null, null, null);

        /*if (ClientCommandManager.instance().getCommandList().contains("UpdateGameList")) {
            List<String> commandList = ClientCommandManager.instance().getCommandList();
            ClientCommandManager.instance().setCommands(commandList);
            //commandList.remove("UpdateGameList");
            return new GetGameListResult(true, null, null, null,
                    ModelRoot.instance().getListGames());
        }
        else
            return new GetGameListResult(true, null, null, null, null);*/
    }
}
