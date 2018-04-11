package com.example.server.Database;

import com.example.server.Plugin.IPlugin;

/**
 * Created by claytonkingsbury on 4/10/18.
 */

public class DeleteGameData {
    private static DeleteGameData _instance;
    private IPlugin plugin;
    public static DeleteGameData instance() {
        if(_instance == null)
            _instance = new DeleteGameData();
        return _instance;
    }
    public void DeleteGame(int gameId){
        plugin = plugin.instance(); // get the instance of the plugin
        try{
            plugin.getGameDao().delete(gameId);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
