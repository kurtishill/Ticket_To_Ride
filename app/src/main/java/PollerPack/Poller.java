package PollerPack;

import java.util.Timer;
import java.util.TimerTask;

//import client_facade.ClientFacade;

/**
 * Created by Clayton Kings on 2/2/2018.
 */

public class Poller {
    void Poll(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                //GetGameListResult result = new ClientFacade().GetGameList();//send HTTP requests
                //ClientModelRoot.instance().setGames(result.getGamesList());
            }
        }, 0, 3000);
    }
}
