package com.example.server.Database;

import com.example.server.Plugin.IPlugin;
import com.example.server.Results.GenericCommand;
import com.example.server.Results.ICommand;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public class StoredData {
    private static StoredData _instance;
    public static StoredData instance() {
        if(_instance == null)
            _instance = new StoredData();
        return _instance;
    }
    private int counter = 0;
    public void Store(ICommand command){
        counter += 1;
        int N = 10;
        if (counter >= N){
            counter = 0;
            // todo store all model data
            // todo I dont think it is possible to make a interface singleton
        }
        else{
            //todo store command
        }
    }
}
