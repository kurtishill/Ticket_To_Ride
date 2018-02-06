package AsyncTasks;

import android.os.AsyncTask;

import com.example.server.Results.JoinGameResult;

import client_model.ClientModelRoot;

/**
 * Created by tnels on 2/6/2018.
 */

public class JoinGameTask extends AsyncTask<String, Void, JoinGameResult> {
    @Override
    protected JoinGameResult doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(JoinGameResult result){
        if(result.isSuccess()){
            ClientModelRoot.instance().setCurrGame(result.getGame());
        }
        super.onPostExecute(result);
    }
}
