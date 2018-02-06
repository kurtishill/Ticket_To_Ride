package AsyncTasks;

import android.os.AsyncTask;

import com.example.server.Results.CreateGameResult;
import com.example.server.Results.RegisterResult;

import client_model.ClientModelRoot;

/**
 * Created by tnels on 2/6/2018.
 */

public class CreateGameTask extends AsyncTask <String, Void, CreateGameResult> {
    @Override
    protected CreateGameResult doInBackground(String... strings) {
        return null;
    }
    protected void onPostExecute(CreateGameResult result){
        if(result.isSuccess()){
            ClientModelRoot.instance().setCurrGame(result.getGame());
        }
        super.onPostExecute(result);
    }
}
