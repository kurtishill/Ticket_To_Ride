package com.example.server.Plugin;

import com.example.server.Database.StoredData;

/**
 * Created by claytonkingsbury on 4/11/18.
 */

public class PluginWrapper {
    public IPlugin plugin;
    private static PluginWrapper _instance;
    public static PluginWrapper instance() {
        if(_instance == null)
            _instance = new PluginWrapper();
        return _instance;
    }
    public void InstallPlugin(IPlugin plugin){
        this.plugin = plugin;
    }

    public IPlugin getPlugin() {
        return plugin;
    }
}
