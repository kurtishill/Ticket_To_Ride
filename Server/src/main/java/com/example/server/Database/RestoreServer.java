package com.example.server.Database;

import com.example.server.ClientCommandManager;
import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Results.ICommand;
import com.example.server.Serializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;

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
        if (players == null)
            return;

        List<GameDTO> games = PluginWrapper.instance().getPlugin().getGameDao().read();
        if (games == null)
            return;

        List<CommandDTO> commandDTOs = PluginWrapper.instance().getPlugin().getCommandDao().read();

        for (int i = 0; i < players.size(); i++) {
            Player newPlayer = new Player();
            newPlayer.setID(players.get(i).getId());
            newPlayer.setUsername(players.get(i).getUsername());
            newPlayer.setPassword(players.get(i).getPassword());
            ModelRoot.instance().addPlayer(newPlayer.getID(), newPlayer);
        }

        for (int i = 0; i < games.size(); i++) {
            Object obj = Serializer.decode(games.get(i).getGame(), TicketToRideGame.class);
            TicketToRideGame game = (TicketToRideGame) obj;
            ModelRoot.instance().addGame(game.getGameID(), game);
        }

        for (int i = 0; i < ModelRoot.instance().getListGames().size(); i++) {
            for (int j = 0; j < ModelRoot.instance().getListGames().get(i).getPlayers().size(); j++) {
                Player player = ModelRoot.instance().getListGames().get(i).getPlayers().get(j);
                ModelRoot.instance().addPlayer(player.getID(), player);
            }
        }

        if (commandDTOs == null)
            return;

        List<ICommand> commands = new ArrayList<>();
        for (int i = 0; i < commandDTOs.size(); i++) {
            ByteArrayInputStream bis = new ByteArrayInputStream(commandDTOs.get(i).getCommand());
            ObjectInput in = null;
            Object obj = null;
            try {
                in = new ObjectInputStream(bis);
                obj = in.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException ex) {
                    // ignore close exception
                }
            }
            commands.add((ICommand) obj);
            ClientCommandManager.instance().addGameCommand(commandDTOs.get(i).getGameId(), "UpdateGameList");
        }

        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).execute();
        }

        for (int i = 0; i < ModelRoot.instance().getListGames().size(); i++) {
            String game = Serializer.encode(ModelRoot.instance().getListGames().get(i));
            GameDTO dto = new GameDTO(ModelRoot.instance().getListGames().get(i).getGameID(), game);
            try {
                PluginWrapper.instance().getPlugin().getGameDao().update(dto);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        PluginWrapper.instance().getPlugin().getCommandDao().clear();
    }
}
