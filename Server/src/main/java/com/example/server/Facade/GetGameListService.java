package com.example.server.Facade;

import com.example.server.ClientCommandManager;
import com.example.server.Model.ModelRoot;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.GetGameListResult;
import com.example.server.Results.ICommand;

import java.util.List;

/**
 * Created by ckingsbu on 1/30/18.
 */

public class GetGameListService {
    public GetGameListResult GetGameList() {
        List<TicketToRideGame> gameList = ModelRoot.instance().getListGames();
        ClientCommandManager.instance().addCommand(ClientCommandManager.instance().GetGameList(gameList));

        List<ICommand> commands = ClientCommandManager.instance().getCommandList();
        return new GetGameListResult(true, null, commands, null, null);
    }
}
