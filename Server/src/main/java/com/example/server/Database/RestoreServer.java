package com.example.server.Database;

import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.ICommand;
import com.example.server.Serializer;

import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

import Plugin.PluginWrapper;
import dto.CommandDTO;
import dto.GameDTO;
import dto.PlayerDTO;

/**
 * Created by kurtishill on 4/13/18.
 */

public class RestoreServer {

    public static void restore() {
        List<PlayerDTO> players = PluginWrapper.instance().getPlugin().getUserDao().read();
        List<GameDTO> games = PluginWrapper.instance().getPlugin().getGameDao().read();
        List<CommandDTO> commandDTOs = PluginWrapper.instance().getPlugin().getCommandDao().read();

        for (int i = 0; i < games.size(); i++) {
            Object obj = Serializer.decode(games.get(i).getGame(), TicketToRideGame.class);
            TicketToRideGame game = (TicketToRideGame) obj;
            ModelRoot.instance().addGame(game.getGameID(), game);
        }

        for (int i = 0; i < ModelRoot.instance().getListGames().size(); i++) {
            for (int j = 0; j < ModelRoot.instance().getListGames().get(i).getPlayers().size(); i++) {
                Player player = ModelRoot.instance().getListGames().get(i).getPlayers().get(j);
                ModelRoot.instance().addPlayer(player.getID(), player);
            }
        }

        List<ICommand> commands = new ArrayList<>();
        for (int i = 0; i < commandDTOs.size(); i++) {
            Object obj = SerializationUtils.deserialize(commandDTOs.get(i).getCommand());
            commands.add((ICommand) obj);
        }

        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).execute();
        }

        PluginWrapper.instance().getPlugin().getCommandDao().clear();
    }
}
