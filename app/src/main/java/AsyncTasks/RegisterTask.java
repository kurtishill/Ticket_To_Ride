package AsyncTasks;

import android.os.AsyncTask;

import com.example.server.Results.RegisterResult;

import client_model.ClientModelRoot;

//import client_facade.ClientFacade;

/**
 * Created by Clayton Kings on 2/2/2018.
 */

public class RegisterTask extends AsyncTask <String, Void, RegisterResult> {
    @Override
    protected RegisterResult doInBackground(String... strings){
        //ClientFacade facade =  new ClientFacade();
        //return facade.Register(strings[0], strings[1]);

        return null;
    }

    @Override
    protected void onPostExecute(RegisterResult registerResult) {
        if(registerResult.isSuccess()){
            ClientModelRoot.instance().setUser(registerResult.getPlayer());
        }
        else {
           /* CharSequence text = loginResult.getErrorMessage();

            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);

            toast.show();*/ // fixme do we want to show a toast here??
        }
        super.onPostExecute(registerResult);
    }
}
