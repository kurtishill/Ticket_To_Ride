package com.example.server.Database;

import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import Plugin.IPlugin;
import Plugin.PluginWrapper;

import com.example.server.Results.GenericCommand;
import com.example.server.Results.ICommand;
import com.example.server.Serializer;

import org.apache.commons.lang3.SerializationUtils;

import dto.CommandDTO;
import dto.GameDTO;
import dto.PlayerDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public class StoredData {
    private static StoredData _instance;
    private IPlugin plugin;
    public static StoredData instance() {
        if(_instance == null)
            _instance = new StoredData();
        return _instance;
    }
    private int counter = 0;
    public void Store(GenericCommand command, int gameId){
        plugin = PluginWrapper.instance().getPlugin(); // get the instance of the plugin
        counter += 1;
        if (counter >= N){
            counter = 0;

            Set<String> users = ModelRoot.instance().getAllPlayers().keySet();
            for (String id : users){
                Player player = ModelRoot.instance().getAllPlayers().get(id);
                String playerId = player.getID();
                String username = player.getUsername();
                String password = player.getPassword();
                try{
                    plugin.getUserDao().create(new PlayerDTO(playerId, username, password, gameId));
                }
                catch(Exception e){
                    try{
                        plugin.getUserDao().update(new PlayerDTO(playerId, username, password, gameId));
                    }
                    catch (Exception E){
                        System.out.println(E.getMessage());
                    }
                }
            }
            List<TicketToRideGame> games = ModelRoot.instance().getListGames();
            for (int i = 0; i < games.size(); i++){
                String game = Serializer.encode(games.get(i));
                GameDTO gameDTO = new GameDTO(games.get(i).getGameID(), game);
                try{
                    plugin.getGameDao().create(gameDTO);
                }
                catch (Exception e){
                    try{
                        plugin.getGameDao().update(gameDTO);
                    }
                    catch (Exception E){
                        System.out.println(E.getMessage());
                    }
                }
            }
            List<Integer> ids = ModelRoot.instance().getCommandIds();
            for (int i = 0; i < ids.size(); i++) {
                plugin.getCommandDao().delete(ids.get(i));
            }
            ModelRoot.instance().getCommandIds().clear(); // dont know if this will actually clear the list
        }
        else{
            int id = ModelRoot.instance().getId();
            byte[] commandByteArray = {};
            try {
                commandByteArray = SerializationUtils.serialize(command);
            }
            catch (Exception ex) {
                System.out.println("Error while serializing command");
                ex.printStackTrace();
            }

            plugin.getCommandDao().create(new CommandDTO(id, commandByteArray, gameId));//todo store command


        }
    }
    private int N = 0;
    public void SetCount(int n){
        N = n;
    }
}
