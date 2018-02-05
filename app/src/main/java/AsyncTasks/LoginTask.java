package AsyncTasks;

import android.os.AsyncTask;

import com.example.server.Results.LoginResult;

import client_model.ClientModelRoot;

//import client_facade.ClientFacade;

/**
 * Created by Clayton Kings on 2/2/2018.
 */

public class LoginTask extends AsyncTask<String, Void, LoginResult> {


    @Override
    protected LoginResult doInBackground(String... strings) {
        //ClientFacade facade =  new ClientFacade();
        //return facade.Login(strings[0], strings[1]);
        return null;
    }

    @Override
    protected void onPostExecute(LoginResult loginResult) {
        if(loginResult.isSuccess()){
            ClientModelRoot.instance().setUser(loginResult.getPlayer());
        }
        else {
           /* CharSequence text = loginResult.getErrorMessage();

            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);

            toast.show();*/ // fixme do we want to show a toast here??
        }
        super.onPostExecute(loginResult);
    }
}
