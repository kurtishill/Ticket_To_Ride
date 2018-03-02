package views_and_presenters;

import com.example.server.Model.TicketToRideGame;

/**
 * Created by HillcollegeMac on 2/7/18.
 */

public interface IGamePresenter {

    TicketToRideGame getGame();

    boolean didGameStart();

    boolean isItUsersTurn();
}
