package com.example.server.Database;

import com.example.server.Model.ModelRoot;
import com.example.server.Model.Player;
import com.example.server.Model.TicketToRideGame;
import com.example.server.Plugin.IPlugin;
import com.example.server.Results.GenericCommand;
import com.example.server.Results.ICommand;
import com.example.server.dto.CommandDTO;

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
    public void Store(ICommand command, int gameId){
        counter += 1;
        int N = 10;
        if (counter >= N){
            counter = 0;
            // todo store all model data
            // todo I dont think it is possible to make a interface singleton
            Set<String> users = ModelRoot.instance().getAllPlayers().keySet();
            for (String id : users){
                try{
                    plugin.getUserDao().create(ModelRoot.instance().getAllPlayers().get(id));
                }
                catch(Exception e){
                    try{
                        plugin.getUserDao().update(ModelRoot.instance().getAllPlayers().get(id));
                    }
                    catch (Exception E){
                        System.out.println(E.getMessage());
                    }
                }
            }
            List<TicketToRideGame> games = ModelRoot.instance().getListGames();
            for (int i = 0; i < games.size(); i++){
                try{
                    plugin.getGameDao().create(games.get(i));
                }
                catch (Exception e){
                    try{
                        plugin.getGameDao().update(games.get(i));
                    }
                    catch (Exception E){
                        System.out.println(E.getMessage());
                    }
                }
            }
            List<Integer> ids = ModelRoot.instance().getCommandIds();
            //todo delete all the commands, should we create a method for this or just do it manually

        }
        else{
            int id = ModelRoot.instance().getId();
            plugin.getCommandDao().create(new CommandDTO(id, command, gameId));//todo store command


        }
    }
}
