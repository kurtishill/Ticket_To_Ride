package PollerPack;

import com.example.server.Results.GetGameListResult;

import java.util.Timer;
import java.util.TimerTask;

import client_facade.ClientFacade;
import client_model.ClientModelRoot;

/**
 * Created by Clayton Kings on 2/2/2018.
 */

public class Poller {
    void Poll(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                GetGameListResult result = new ClientFacade().GetGameList();//send HTTP requests
                ClientModelRoot.instance().setGames(result.getGames());
            }
        }, 0, 3000);
    }
}
