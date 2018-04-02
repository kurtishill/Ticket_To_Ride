package com.example.server.Facade;

import com.example.server.Model.ModelRoot;
import com.example.server.Results.DeleteGameResult;

/**
 * Created by kurtishill on 3/14/18.
 */

public class DeleteGameService {

    public DeleteGameResult deleteGame(Integer gameId) {
        try {
            ModelRoot.instance().deleteGame(gameId);
        }
        catch (Exception ex) {
            return new DeleteGameResult(false, ex.getMessage(), null, "Exception");
        }
        return new DeleteGameResult(true, null, null, null);
    }
}
