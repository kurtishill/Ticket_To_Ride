package com.example.server.Database;

import com.example.server.Plugin.IPlugin;
import com.example.server.Results.GenericCommand;

/**
 * Created by claytonkingsbury on 4/8/18.
 */

public class StoredData {
    private int counter = 0;
    public void Store(GenericCommand command){
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
