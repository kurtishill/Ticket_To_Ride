package com.example.server.Facade;

import com.example.server.ClientCommandManager;
import com.example.server.Model.ModelRoot;
import com.example.server.Results.GetGameListResult;

import java.util.List;

/**
 * Created by ckingsbu on 1/30/18.
 */

public class GetGameListService {
    public GetGameListResult GetGameList() {
        if (ClientCommandManager.instance().getCommandList().contains("UpdateGameList")) {
            List<String> commandList = ClientCommandManager.instance().getCommandList();
            ClientCommandManager.instance().setCommands(commandList);
            //commandList.remove("UpdateGameList");
            return new GetGameListResult(true, null, null, null,
                    ModelRoot.instance().getListGames());
        }
        else
            return new GetGameListResult(true, null, null, null, null);
    }
}
