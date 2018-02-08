package com.example.server.Facade;

import com.example.server.Model.ModelRoot;
import com.example.server.Results.GetGameListResult;

/**
 * Created by ckingsbu on 1/30/18.
 */

public class GetGameListService {
    public GetGameListResult GetGameList(String username){
        if(ModelRoot.instance().UserExists(username) != null){
            return new GetGameListResult(true, null, null, null, ModelRoot.instance().getListGames());
        }
        return new GetGameListResult(false, "Username Invalid", null, "invalidUsername", null);
    }
}
